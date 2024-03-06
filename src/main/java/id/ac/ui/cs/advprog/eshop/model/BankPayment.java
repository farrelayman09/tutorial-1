package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class BankPayment extends Payment{
    public BankPayment(String id, String method, Order order, Map<String, String> paymentData) {
        super(id, method, order, paymentData);
        setPaymentData(paymentData);
    }

    public BankPayment(String id, String method, Order order, Map<String, String> paymentData, String status) {
        super(id, method, order, paymentData, status);
        setPaymentData(paymentData);

    }

    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData == null || paymentData.isEmpty()) {
            throw new IllegalArgumentException("Empty Payment!");
        } else if (paymentData.get("bankName") == null || paymentData.get("bankName").isEmpty()) {
            throw new IllegalArgumentException("Empty bankName!");
        } else if (paymentData.get("referenceCode") == null || paymentData.get("referenceCode").isEmpty()) {
            throw new IllegalArgumentException("Empty bankName!");
        }
    }
}
