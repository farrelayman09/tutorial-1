package id.ac.ui.cs.advprog.eshop.model;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
public class Order {
    String id;
    List<Product> products;
    Long orderTime;
    String author;
    @Setter
    String status;
    public Order(String id, List<Product> products, Long orderTime, String author) {

    }

    public Order(String id, List<Product> products, Long orderTime, String author, String status) {

    }

//     this.id = id;
//        this.orderTime = orderTime;
//        this.author = author;
//        this.status = "WAITING_PAYMENT";
//
//        if (product.isEmpty()) {
//        throw new IllegalArgumentException();
//    } else {
//        this.products = products;
//    }
}
