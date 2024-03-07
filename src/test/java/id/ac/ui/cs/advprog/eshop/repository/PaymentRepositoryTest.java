package id.ac.ui.cs.advprog.eshop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.ac.ui.cs.advprog.eshop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

class PaymentRepositoryTest {
    Payment voucherPayment;
    Payment bankPayment;
    PaymentRepository paymentRepository;
    Order order;
    List<Product> products;
    List<Payment> payments;

    @BeforeEach
    void setup() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();
        products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-468e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-98b1-437d-a0bf-d0821dde9896");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        products.add(product2);
        order = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e",
                products, 1708560000L, "Safira Sudrajat");


        Map<String, String> voucherPaymentData = new HashMap<>();
        voucherPaymentData.put("voucherCode", "ESHOP1234ABC5678");
        voucherPayment = new VoucherPayment("a2a5b551-112b-4c0f-d546-84ea1396c79d",
                PaymentMethod.VOUCHER.getValue(), order, voucherPaymentData);

        Map<String, String> bankPaymentData = new HashMap<>();
        bankPaymentData.put("bankName", "Mandiri");
        bankPaymentData.put("referenceCode", "123456789");
        bankPayment = new BankPayment("a2a5b551-112b-4c0f-d546-84ea1396c79e",
                PaymentMethod.BANK.getValue(), order, bankPaymentData);

        payments.add(voucherPayment);
        payments.add(bankPayment);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertEquals(PaymentStatus.PROCESSING.getValue(), payment.getStatus());
    }

    @Test
    void testSaveCreateVoucherMethod() {
        Payment payment = payments.get(0);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(PaymentStatus.PROCESSING.getValue(), payment.getStatus());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
    }

    @Test
    void testSaveCreatePaymentDuplicateId(){
        Payment payment = payments.get(1);
        paymentRepository.save(payment);
        assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.save(payment);
        });
    }

    @Test
    void testSaveCreateBankPaymentDuplicateId() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);
        assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.save(payment);
        });
    }

    @Test
    void testSaveCreateVoucherPaymentDuplicateId() {
        Payment payment = payments.get(0);
        paymentRepository.save(payment);
        assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.save(payment);
        });
    }

    @Test
    void testGetAllPayments() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> allPayments = paymentRepository.getAllPayments();
        assertEquals(2, allPayments.size());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertSame(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getOrder(), findResult.getOrder());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        assertNull(paymentRepository.findById("foobar"));
    }
}