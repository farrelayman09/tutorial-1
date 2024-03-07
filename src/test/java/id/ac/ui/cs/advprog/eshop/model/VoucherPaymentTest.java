package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class VoucherPaymentTest {
    private Map<String, String> paymentData;
    private Order order;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4137-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        products.add(product1);
        products.add(product2);

        order = new Order(
                "dbd4aff4-9a7f-4603-92c2-eaf529271cc9",
                products,
                1708560000L,
                "Safira Sudrajat"
        );

        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreateVoucherPaymentDefaultStatus() {
        Payment payment = new VoucherPayment("a2a5b551-112b-4c0f-d546-84ea1396c79e",
                PaymentMethod.VOUCHER.getValue(), order, paymentData
        );

        assertEquals("a2a5b551-112b-4c0f-d546-84ea1396c79e", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertSame(order, payment.getOrder());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.PROCESSING.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new VoucherPayment("a2a5b551-112b-4c0f-d546-84ea1396c79e",
                PaymentMethod.BANK.getValue(), order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentRejectedStatus() {
        Payment payment = new VoucherPayment("a2a5b551-112b-4c0f-d546-84ea1396c79e",
                PaymentMethod.BANK.getValue(), order, paymentData, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateVoucherPaymentWithEmptyVoucherCode() {
        paymentData.put("voucherCode", "");
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new VoucherPayment("a2a5b551-112b-4c0f-d546-84ea1396c79e",
                    PaymentMethod.VOUCHER.getValue(), order, paymentData
            );
        });
    }

    @Test
    void testCreateVoucherPaymentWithLongerVoucherCode() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678123456");
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new VoucherPayment("a2a5b551-112b-4c0f-d546-84ea1396c79e",
                    PaymentMethod.VOUCHER.getValue(), order, paymentData
            );
        });
    }

    @Test
    void testCreateVoucherPaymentWithNoNumericalInVoucherCode() {
        paymentData.put("voucherCode", "ESHOPESHOPESHOPESHOP");
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new VoucherPayment("a2a5b551-112b-4c0f-d546-84ea1396c79e",
                    PaymentMethod.VOUCHER.getValue(), order, paymentData
            );
        });
    }

    @Test
    void testCreateVoucherPaymentWithESHOPAtTheEndOfVoucherCode() {
        paymentData.put("voucherCode", "1234ABC5678ESHOP");
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new VoucherPayment("a2a5b551-112b-4c0f-d546-84ea1396c79e",
                    PaymentMethod.VOUCHER.getValue(), order, paymentData
            );
        });
    }
}
