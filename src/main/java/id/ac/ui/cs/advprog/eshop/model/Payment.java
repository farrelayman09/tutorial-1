package id.ac.ui.cs.advprog.eshop.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    }

    public Payment(String id, String method, Order order, Map<String,String> paymentData, String status) {

    }

}


//    Payment payment = new Payment("a2a5b551-112b-4c0f-d546-84ea1396c79e",
//            PaymentMethod.BANK.getValue(), order, paymentData, PaymentStatus.SUCCESS.getValue());