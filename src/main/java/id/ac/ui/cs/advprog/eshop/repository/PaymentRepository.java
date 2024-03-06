package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment) {
        int i = 0;
        for (Payment savedPayment : paymentData) {
            //replace existing payment
            if (savedPayment.getId().equals(payment.getId())) {
                paymentData.remove(i);
                paymentData.add(i, payment);
                return payment;
            }
            i += 1;
        }
        paymentData.add(payment);
        return payment;
    }
    public Payment findById(String id){
        for (Payment payment : paymentData){
            if (payment.getId().equals(id)){
                return payment;
            }
        }
        return null;
    }
    public List<Payment> findAllByVoucherCode(){
        List<Payment> vcResult = new ArrayList<>();
        for (Payment savedPayment : paymentData) {
            if (savedPayment.getMethod().equals("VOUCHER_CODE")) {
                vcResult.add(savedPayment);
            }
        }
        return vcResult;
    }
    public List<Payment> findAllByCashOnDelivery(){
        List<Payment> codResult = new ArrayList<>();
        for (Payment savedPayment : paymentData) {
            if (savedPayment.getMethod().equals("CASH_ON_DELIVERY")) {
                codResult.add(savedPayment);
            }
        }
        return codResult;
    }
    public List<Payment> getAllPayment(){
        return paymentData;
    }
}