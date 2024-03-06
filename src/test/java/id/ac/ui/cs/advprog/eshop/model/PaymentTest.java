package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private List<Product> products;
    private List<Order> orders;

    @BeforeEach
    void setUp(){
        this.products = new ArrayList<>();
        this.orders = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);

        this.products.add(product1);
        this.products.add(product2);

        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat");
        Order order2 = new Order("52629791-021f-4j72-h986-04eg1796d23y",
                this.products, 1708590000L, "Bambang Herlambang");

        orders.add(order1);
        orders.add(order2);
    }

    @Test
    void testCreatePaymentEmptyMethod() {
        Map<String, String> paymentData = new HashMap<String, String>();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("18353427-09av-40p7-b504-5o902399d79b",
                    "", this.orders.getLast(), paymentData);
        });
    }

    @Test
    void testCreatePaymentUnavailableMethod(){
        Map<String, String> paymentData = new HashMap<String, String>();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("18353427-09av-40p7-b504-5o902399d79b",
                    "PAYLATER", this.orders.getFirst(), paymentData);
        });
    }

    @Test
    void testCreatePaymentEmptyPaymentData() {
        Map<String, String> paymentData = new HashMap<String, String>();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("18353427-09av-40p7-b504-5o902399d79b",
                    "VOUCHER_CODE", this.orders.getLast(), paymentData);
        });
    }

    @Test
    void testCreateOrderEmptyOrder(){
        orders.clear();
        Map<String, String> paymentData = new HashMap<String, String>();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("18353427-09av-40p7-b504-5o902399d79b",
                    "CASH_ON_DELIVERY", this.orders.getFirst(), paymentData);
        });
    }

    @Test
    void testCreateVoucherCodePaymentSuccess() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("18353427-09av-40p7-b504-5o902399d79b", "VOUCHER_CODE",
                this.orders.getLast(), paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreateVoucherCodePaymentViolate16Characters() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567810");
        Payment payment = new Payment("18353427-09av-40p7-b504-5o902399d79b", "VOUCHER_CODE",
                this.orders.getFirst(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreateVoucherCodePaymentNotStartedWithESHOP() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "DAUN1234AB5678");
        Payment payment = new Payment("18353427-09av-40p7-b504-5o902399d79b", "VOUCHER_CODE",
                this.orders.getFirst(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreateVoucherCodePaymentNotContains8NumericalCharacters() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D");
        Payment payment = new Payment("18353427-09av-40p7-b504-5o902399d79b", "VOUCHER_CODE",
                this.orders.getLast(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusVoucherCodePaymentSuccess() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234IBC567D");
        Payment payment = new Payment("18353427-09av-40p7-b504-5o902399d79b", "VOUCHER_CODE",
                this.orders.getFirst(), paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getOrder().getStatus());
    }

    @Test
    void testSetStatusVoucherCodePaymentRejected() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D");
        Payment payment = new Payment("18353427-09av-40p7-b504-5o902399d79b", "VOUCHER_CODE",
                this.orders.getFirst(), paymentData);
        payment.setStatus("REJECTED");
        assertEquals("FAILED", payment.getOrder().getStatus());
    }

    @Test
    void testCreateCashOnDeliveryPaymentSuccess() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("address", "Jalan Beji");
        paymentData.put("deliveryFee", "15000");
        Payment payment = new Payment("18353427-09av-40p7-b504-5o902399d79b",
                "CASH_ON_DELIVERY", this.orders.getFirst(), paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreateCashOnDeliveryPaymentRejectedMissingAddress() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("deliveryFee", "12000");
        paymentData.put("address", "");
        Payment payment = new Payment("18353427-09av-40p7-b504-5o902399d79b",
                "CASH_ON_DELIVERY", this.orders.getLast(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreateCashOnDeliveryPaymentRejectedMissingDeliveryFee() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("address", "Jalan Kebenaran");
        Payment payment = new Payment("6c93d3e2-b009-46ba-9d15-f03d85adc2de",
                "CASH_ON_DELIVERY", this.orders.getFirst(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusCashOnDeliveryPaymentSuccess() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("address", "Jalan Pijar");
        paymentData.put("deliveryFee", "25000");
        Payment payment = new Payment("6c93d3e2-b009-46ba-9d15-f03d85adc2de",
                "CASH_ON_DELIVERY", this.orders.getLast(), paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getOrder().getStatus());
    }

    @Test
    void testSetStatusPaymentCashOnDeliveryRejected() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("address", "Jalan Pulang");
        paymentData.put("deliveryFee", "8000");
        Payment payment = new Payment("6c93d3e2-b009-46ba-9d15-f03d85adc2de",
                "CASH_ON_DELIVERY", this.orders.getFirst(), paymentData);
        payment.setStatus("REJECTED");
        assertEquals("FAILED", payment.getOrder().getStatus());
    }

}
