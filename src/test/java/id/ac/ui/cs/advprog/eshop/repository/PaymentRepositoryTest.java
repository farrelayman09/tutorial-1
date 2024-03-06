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
        product1.setProductId("e45d7d21-fd29-4533-a569-abbe0819579a");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Product product2 = new Product();
        product2.setProductId("8a76b99c-a0b3-46d2-a688-4c1831b21119");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        products.add(product2);
        order = new Order("dbd4aff4-9a7f-4603-92c2-eaf529271cc9",
                products, 1708560000L, "Safira Sudrajat");

        Payment payment1 = new Payment("a0f81308-9911-40c5-8da4-fa3194485aa1",
                "", order, null);
        Payment payment2 = new Payment("b0f81308-9911-40c5-8da4-fa3194485aa1",
                        "", order, null);
        payments.add(payment1);
        payments.add(payment2);

        Map<String, String> voucherPaymentData = new HashMap<>();
        voucherPaymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment voucherPayment = new VoucherPayment("a2a5b551-112b-4c0f-d546-84ea1396c79e",
                PaymentMethod.VOUCHER.getValue(), order, voucherPaymentData);

        Map<String, String> bankPaymentData = new HashMap<>();
        bankPaymentData.put("bankName", "Mandiri");
        bankPaymentData.put("referenceCode", "123456789");
        Payment bankPayment = new BankPayment("a2a5b551-112b-4c0f-d546-84ea1396c79e",
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
        Payment payment = payments.get(2);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(2).getId());
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
        Payment payment = payments.get(3);
        paymentRepository.save(payment);
        assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.save(payment);
        });
    }

    @Test
    void testSaveCreateVoucherPaymentDuplicateId() {
        Payment payment = payments.get(2);
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
        assertEquals(4, allPayments.size());
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