package edu.haramaya.cosc3053.day8;

public class PaymentGatewayDemo {

    public interface Payable {
        boolean processPayment(double amount);
        String getReceipt();
        default void logTrace(double amount) {
            System.out.println("[AUDIT] Processing payment: $" + amount);
        }
    }

    public static abstract class AbstractGateway implements Payable {
        private final String merchantId;
        public AbstractGateway(String merchantId) { this.merchantId = merchantId; }
        public String getMerchantId() { return merchantId; }
        public abstract double calculateFee(double amount);
    }

    // TODO 8.1: CreditCardGateway extending AbstractGateway
    public static class CreditCardGateway extends AbstractGateway {
        private String receipt = "No payment processed yet.";

        public CreditCardGateway(String merchantId) {
            super(merchantId);
        }

        // TODO 8.2: 2.5% fee
        @Override
        public double calculateFee(double amount) {
            return amount * 0.025;
        }

        // TODO 8.3: processPayment and getReceipt
        @Override
        public boolean processPayment(double amount) {
            if (amount <= 0.0) {
                return false;
            }
            logTrace(amount);
            double fee = calculateFee(amount);
            receipt = String.format("Merchant %s | Paid: $%.2f | Fee: $%.2f | Total: $%.2f",
                    getMerchantId(), amount, fee, amount + fee);
            return true;
        }

        @Override
        public String getReceipt() {
            return receipt;
        }
    }

    public static void main(String[] args) {
        System.out.println("Day 08 Interfaces & Abstract Classes Challenge");
    }
}