package edu.haramaya.cosc3053.day6;

public class BankAccount {

    // 1.1 Private encapsulated fields
    private final String accountNumber;   // read-only after creation
    private String accountHolder;
    private double balance;

    // 1.2 Constructor with defensive validation
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be null or blank.");
        }
        if (accountHolder == null || accountHolder.isBlank()) {
            throw new IllegalArgumentException("Account holder cannot be null or blank.");
        }
        if (initialBalance < 0.0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // 1.3 Deposit: amount must be > 0.0
    public boolean deposit(double amount) {
        if (amount <= 0.0) {
            System.out.printf("❌ [REJECTED] Deposit $%.2f into %s -> Amount must be positive.%n",
                    amount, accountNumber);
            return false;
        }
        balance += amount;
        System.out.printf("✅ [SUCCESS] Deposited $%.2f into %s -> New Balance: $%.2f%n",
                amount, accountNumber, balance);
        return true;
    }

    // 1.4 Withdraw: amount must be > 0.0 and <= balance
    public boolean withdraw(double amount) {
        if (amount <= 0.0) {
            System.out.printf("❌ [REJECTED] Withdraw $%.2f from %s -> Amount must be positive.%n",
                    amount, accountNumber);
            return false;
        }
        if (amount > balance) {
            System.out.printf("❌ [REJECTED] Withdraw $%.2f from %s -> Overdraft Blocked! Balance ($%.2f)%n",
                    amount, accountNumber, balance);
            return false;
        }
        balance -= amount;
        System.out.printf("✅ [SUCCESS] Withdrew $%.2f from %s -> New Balance: $%.2f%n",
                amount, accountNumber, balance);
        return true;
    }

    // 1.5 Getters only (NO setBalance!)
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }
}