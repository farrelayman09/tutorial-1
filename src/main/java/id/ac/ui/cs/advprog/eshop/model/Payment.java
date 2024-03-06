package id.ac.ui.cs.advprog.eshop.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
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


//    Payment payment = new Payment("a2a5b551-112b-4c0f-d546-84ea1396c79e",
//            PaymentMethod.BANK.getValue(), order, paymentData, PaymentStatus.SUCCESS.getValue());