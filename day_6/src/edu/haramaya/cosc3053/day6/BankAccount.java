package edu.haramaya.cosc3053.day6;

public class BankAccount {
    // TODO 5.1: private encapsulated fields
    private final String accountNumber;
    private String accountHolder;
    private double balance;
    private static final double MINIMUM_BALANCE = 25.00;

    // TODO 5.2: constructor with defensive validation
    public BankAccount(String accountNumber, String accountHolder, double initialDeposit) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be null or blank.");
        }
        if (accountHolder == null || accountHolder.isBlank()) {
            throw new IllegalArgumentException("Account holder cannot be null or blank.");
        }
        if (initialDeposit < MINIMUM_BALANCE) {
            throw new IllegalArgumentException("Initial deposit must be at least $25.00.");
        }
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
    }

    // TODO 5.3: deposit
    public boolean deposit(double amount) {
        if (amount <= 0.0) {
            System.out.printf("❌ [REJECTED] Deposit of $%.2f denied: amount must be positive.%n", amount);
            return false;
        }
        balance += amount;
        System.out.printf("✅ [SUCCESS] Deposited $%.2f -> New Balance: $%.2f%n", amount, balance);
        return true;
    }

    // TODO 5.4: withdraw
    public boolean withdraw(double amount) {
        if (amount <= 0.0) {
            System.out.printf("❌ [REJECTED] Withdrawal of $%.2f denied: amount must be positive.%n", amount);
            return false;
        }
        if (balance - amount < MINIMUM_BALANCE) {
            System.out.printf("❌ [REJECTED] Withdrawal of $%.2f denied: Remaining balance ($%.2f) would fall below $25.00 minimum balance requirement.%n",
                    amount, balance - amount);
            return false;
        }
        balance -= amount;
        System.out.printf("✅ [SUCCESS] Withdrew $%.2f -> New Balance: $%.2f%n", amount, balance);
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC-101", "Abebe Bekele", 500.00);
        acc.deposit(150.00);
        acc.withdraw(100.00);
        acc.withdraw(600.00); // Should fail minimum balance check ($25.00)
    }
}
