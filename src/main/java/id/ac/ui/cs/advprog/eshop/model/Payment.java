package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {
    String id;
    String method;
    Order order;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Order order, Map<String,String> paymentData) {
        this.id = id;
        this.method = method;
        this.order = order;
        this.paymentData = paymentData;
        this.status = PaymentStatus.PROCESSING.getValue();

        if (paymentData.isEmpty() || order == null) {
            throw new IllegalArgumentException();
        }
    }

    public Payment(String id, String method, Order order, Map<String,String> paymentData, String status) {
        this(id,method, order, paymentData);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

}