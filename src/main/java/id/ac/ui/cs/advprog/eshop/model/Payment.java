package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(String id, String method, Order oder, Map<String, String> paymentData){
    }

    public Payment(String id, String method, String status, Order order, Map<String, String> paymentData){
    }

    public void setStatus(String status){
    }
}
