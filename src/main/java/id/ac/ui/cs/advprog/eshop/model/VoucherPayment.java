package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class VoucherPayment extends Payment{
    public VoucherPayment(String id, String method, Order order, Map<String, String> paymentData) {
        super(id, method, order, paymentData);
        setPaymentData(paymentData);
    }

    public VoucherPayment(String id, String method, Order order, Map<String, String> paymentData, String status) {
        super(id, method, order, paymentData, status);
        setPaymentData(paymentData);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData == null || paymentData.isEmpty()) {
            throw new IllegalArgumentException("Empty Payment!");
        }
        else if (paymentData.get("voucherCode") == null || paymentData.get("voucherCode").isEmpty()) {
            throw new IllegalArgumentException("Empty Voucher Code!");
        }
        else if (!isVoucherCodeValid(paymentData.get("voucherCode"))) {
            throw new IllegalArgumentException("Invalid Voucher Code!");
        }

        this.paymentData = paymentData;
    }


    private long numericsCount(String voucherCode) {
        long count = 0;
        for (int i = 0; i < voucherCode.length(); i++) {
            char c = voucherCode.charAt(i);
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }


    private boolean isVoucherCodeValid(String voucherCode) {
        return voucherCode.length() == 16 && voucherCode.startsWith("ESHOP") &&
                numericsCount(voucherCode) == 8;
    }
}
