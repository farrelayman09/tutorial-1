package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class BankPayment extends Payment{
    public BankPayment(String id, String method, Order order, Map<String, String> paymentData) {
        super(id, method, order, paymentData);
    }

    public BankPayment(String id, String method, Order order, Map<String, String> paymentData, String status) {
        super(id, method, order, paymentData, status);
    }
}
