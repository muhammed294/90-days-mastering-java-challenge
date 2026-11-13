package edu.haramaya.cosc3053.day8;
import java.util.Locale;

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

    // TODO 8.1: CreditCardGateway extends AbstractGateway
    public static class CreditCardGateway extends AbstractGateway {

        private static final double FEE_RATE = 0.025;   // 2.5%
        private String lastReceipt = "No payment processed yet.";

        public CreditCardGateway(String merchantId) {
            super(merchantId);   // sets merchantId in the parent
        }

        // TODO 8.2: 2.5% fee
        @Override
        public double calculateFee(double amount) {
            return amount * FEE_RATE;
        }

        // TODO 8.3: processPayment + getReceipt
        @Override
        public boolean processPayment(double amount) {
            if (!(amount > 0.0) || !Double.isFinite(amount)) {   // also rejects NaN and infinity
                lastReceipt = "DECLINED: amount must be a positive number.";
                return false;
            }
            logTrace(amount);                       // default method from Payable
            double fee = calculateFee(amount);
            double total = amount + fee;
            lastReceipt = String.format(Locale.US,
                    "RECEIPT | Merchant: %s | Amount: $%.2f | Fee (2.5%%): $%.2f | Total: $%.2f",
                    getMerchantId(), amount, fee, total);
            return true;
        }

        @Override
        public String getReceipt() {
            return lastReceipt;
        }
    }

    // OPTIONAL extra: a second gateway to show polymorphism
    public static class MobileMoneyGateway extends AbstractGateway {
        private String lastReceipt = "No payment processed yet.";

        public MobileMoneyGateway(String merchantId) { super(merchantId); }

        @Override
        public double calculateFee(double amount) { return amount * 0.01; }   // 1%

        @Override
        public boolean processPayment(double amount) {
            if (!(amount > 0.0) || !Double.isFinite(amount)) {
                lastReceipt = "DECLINED: amount must be a positive number.";
                return false;
            }
            logTrace(amount);
            double fee = calculateFee(amount);
            lastReceipt = String.format(Locale.US,
                    "MOBILE RECEIPT | Merchant: %s | Amount: $%.2f | Fee (1%%): $%.2f",
                    getMerchantId(), amount, fee);
            return true;
        }

        @Override
        public String getReceipt() { return lastReceipt; }
    }

    // Extra demo: run it by passing the program argument "demo"
    private static void runDemo() {
        System.out.println("--- Polymorphism demo ---");
        Payable[] methods = {
                new CreditCardGateway("MERCH-001"),
                new MobileMoneyGateway("MERCH-002")
        };
        for (Payable p : methods) {
            boolean ok = p.processPayment(100.00);
            System.out.println("Success? " + ok);
            System.out.println(p.getReceipt());
        }

        System.out.println("--- Invalid amount ---");
        Payable card = new CreditCardGateway("MERCH-001");
        System.out.println("Success? " + card.processPayment(-20.00));
        System.out.println(card.getReceipt());
    }

    public static void main(String[] args) {
        System.out.println("Day 08 Interfaces & Abstract Classes Challenge");
        if (args.length > 0 && args[0].equals("demo")) {
            runDemo();
        }
    }
}
