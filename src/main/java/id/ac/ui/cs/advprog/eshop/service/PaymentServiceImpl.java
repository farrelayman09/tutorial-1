package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService {

    PaymentRepository paymentRepository;
    @Override
    public Payment addPayment(Order order, String method, Map<String, String> data) {
        return null;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        return null;
    }

    @Override
    public Payment getPayment(String id) {
        return null;
    }

    @Override
    public List<Payment> getAllPayments() {
        return null;
    }
}
