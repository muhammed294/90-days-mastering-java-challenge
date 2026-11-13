package edu.haramaya.cosc3053.day7;

public class Account {

    private final String accountNumber;
    private final String holderName;
    private double balance;

    public Account(String accountNumber, String holderName, double initialBalance) {
        this.accountNumber = requireText(accountNumber, "Account number");
        this.holderName = requireText(holderName, "Account holder");
        if (!(initialBalance >= 0.0)) {   // also rejects NaN
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
    }

    // Small helper: removes the repeated null/blank check
    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or blank.");
        }
        return value;
    }

    public boolean deposit(double amount) {
        if (!(amount > 0.0) || !Double.isFinite(amount)) {
            return false;
        }
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (!(amount > 0.0) || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }
}