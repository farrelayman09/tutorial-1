package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.*;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Order> orders;
    Order order;
    List<Payment> payments;


    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-aebc-c3af385fb078",
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);

        Map<String, String> voucherPaymentData = new HashMap<>();
        voucherPaymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment voucherPayment = new VoucherPayment(
                "a2a5b551-112b-4c0f-d546-84ea1396c79e", PaymentMethod.VOUCHER.getValue(), order1,
                voucherPaymentData);

        Map<String, String> bankPaymentData = new HashMap<>();
        bankPaymentData.put("bankName", "Mandiri");
        bankPaymentData.put("referenceCode", "123456789");
        Payment bankPayment = new BankPayment(
                "a2a5b551-112b-4c0f-d546-84ea1396c79d", PaymentMethod.BANK.getValue(), order2,
                bankPaymentData);

    }

    @Test
    void testAddVoucherPayment() {
        Payment voucherPayment = payments.get(0);
        doReturn(voucherPayment).when(paymentRepository).save(any(Payment.class));
        voucherPayment = paymentService.addPayment(voucherPayment.getOrder(),
                voucherPayment.getMethod(), voucherPayment.getPaymentData());

        doReturn(voucherPayment).when(paymentRepository).findById(voucherPayment.getId());
        Payment result = paymentService.getPayment(voucherPayment.getId());

        assertEquals(voucherPayment.getId(), result.getId());
        assertEquals(voucherPayment.getOrder(), result.getOrder());
        assertEquals(voucherPayment.getPaymentData(), result.getPaymentData());
        assertEquals(voucherPayment.getMethod(), result.getMethod());
        assertEquals(PaymentMethod.VOUCHER.getValue(), result.getMethod());
        verify(paymentService, times(1)).addPayment(any(
                Order.class), any(String.class), any(Map.class));
    }

    @Test
    void testAddBankPayment() {
        Payment bankPayment = payments.get(1);
        doReturn(bankPayment).when(paymentRepository).save(any(Payment.class));
        bankPayment = paymentService.addPayment(bankPayment.getOrder(),
                bankPayment.getMethod(), bankPayment.getPaymentData()
        );

        doReturn(bankPayment).when(paymentRepository).findById(bankPayment.getId());
        Payment result = paymentService.getPayment(bankPayment.getId());

        assertEquals(bankPayment.getId(), result.getId());
        assertEquals(bankPayment.getOrder(), result.getOrder());
        assertEquals(bankPayment.getPaymentData(), result.getPaymentData());
        assertEquals(bankPayment.getMethod(), result.getMethod());
        assertEquals(PaymentMethod.BANK.getValue(), result.getMethod());
        verify(paymentService, times(1)).addPayment(any(
                Order.class), any(String.class), any(Map.class));
    }

    @Test
    void testUpdateStatusBankPayment() {
        Payment bankPayment = payments.get(1);
        doReturn(bankPayment).when(paymentRepository).save(any(Payment.class));
        bankPayment = paymentService.addPayment(bankPayment.getOrder(), bankPayment.getMethod(),
                bankPayment.getPaymentData());

        doReturn(bankPayment).when(paymentRepository).findById(bankPayment.getId());
        Payment result = paymentService.getPayment(bankPayment.getId());

        assertEquals(result.getStatus(), PaymentStatus.PROCESSING.getValue());
        paymentService.setStatus(result, PaymentStatus.SUCCESS.getValue());
        assertEquals(result.getStatus(), PaymentStatus.SUCCESS.getValue());
        assertEquals(OrderStatus.SUCCESS.getValue(), result.getOrder().getStatus());
        assertEquals(PaymentMethod.BANK.getValue(), result.getMethod());

        paymentService.setStatus(result, PaymentStatus.REJECTED.getValue());
        assertEquals(result.getStatus(), PaymentStatus.REJECTED.getValue());
        assertEquals(OrderStatus.FAILED.getValue(), result.getOrder().getStatus());
        assertEquals(PaymentMethod.BANK.getValue(), result.getMethod());
    }

    @Test
    void testUpdateStatusVoucherPayment() {
        Payment voucherPayment = payments.get(0);
        doReturn(voucherPayment).when(paymentRepository).save(any(Payment.class));
        voucherPayment = paymentService.addPayment(voucherPayment.getOrder(), voucherPayment.getMethod(),
                voucherPayment.getPaymentData());


        doReturn(voucherPayment).when(paymentRepository).findById(voucherPayment.getId());
        Payment result = paymentService.getPayment(voucherPayment.getId());

        assertEquals(result.getStatus(), PaymentStatus.PROCESSING.getValue());
        paymentService.setStatus(result, PaymentStatus.SUCCESS.getValue());
        assertEquals(result.getStatus(), PaymentStatus.SUCCESS.getValue());
        assertEquals(OrderStatus.SUCCESS.getValue(), result.getOrder().getStatus());
        assertEquals(PaymentMethod.VOUCHER.getValue(), result.getMethod());

        paymentService.setStatus(result, PaymentStatus.REJECTED.getValue());
        assertEquals(result.getStatus(), PaymentStatus.REJECTED.getValue());
        assertEquals(OrderStatus.FAILED.getValue(), result.getOrder().getStatus());
        assertEquals(PaymentMethod.VOUCHER.getValue(), result.getMethod());
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).getAllPayments();
        List<Payment> result = paymentService.getAllPayments();
        assertEquals(payments, result);
    }
}