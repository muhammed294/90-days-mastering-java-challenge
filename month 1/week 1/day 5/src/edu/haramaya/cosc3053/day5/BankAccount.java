package edu.haramaya.cosc3053.day5;

public class BankAccount {

    // 1. Encapsulated Private Instance Fields
    private final String accountNumber;   // Read-only immutable identifier
    private String accountHolderName;
    private double balance;

    // Class Invariant Constant
    public static final double MINIMUM_BALANCE = 25.00;

    // 2. Multi-Argument Constructor with Defensive Validation
    public BankAccount(String accountNumber, String accountHolderName, double initialDeposit) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be null or blank.");
        }
        if (accountHolderName == null || accountHolderName.isBlank()) {
            throw new IllegalArgumentException("Account holder name cannot be null or blank.");
        }
        if (initialDeposit < MINIMUM_BALANCE) {
            throw new IllegalArgumentException(String.format(
                    "Initial deposit ($%.2f) is below the minimum required balance ($%.2f).",
                    initialDeposit, MINIMUM_BALANCE));
        }
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
    }

    // 3. Deposit Operation with Positive Amount Validation
    public boolean deposit(double amount) {
        if (amount <= 0.0) {
            System.out.printf("❌ [REJECTED] Deposit failed for %s: Amount ($%.2f) must be positive.%n",
                    accountNumber, amount);
            return false;
        }
        this.balance += amount;
        System.out.printf("✅ [SUCCESS] Deposited $%.2f into %s. New Balance: $%.2f%n",
                amount, accountNumber, this.balance);
        return true;
    }

    // 4. Withdrawal Operation with Overdraft & Minimum Balance Invariant Guard
    public boolean withdraw(double amount) {
        if (amount <= 0.0) {
            System.out.printf("❌ [REJECTED] Withdrawal failed for %s: Amount ($%.2f) must be positive.%n",
                    accountNumber, amount);
            return false;
        }
        if ((this.balance - amount) < MINIMUM_BALANCE) {
            System.out.printf("❌ [REJECTED] Withdrawal of $%.2f from %s denied. "
                            + "Remaining balance ($%.2f) would violate minimum required balance ($%.2f).%n",
                    amount, accountNumber, (this.balance - amount), MINIMUM_BALANCE);
            return false;
        }
        this.balance -= amount;
        System.out.printf("✅ [SUCCESS] Withdrew $%.2f from %s. New Balance: $%.2f%n",
                amount, accountNumber, this.balance);
        return true;
    }

    // 5. Account Transfer (withdraw first, deposit only if withdraw succeeded)
    public boolean transferTo(BankAccount targetAccount, double amount) {
        if (targetAccount == null) {
            System.out.printf("❌ [REJECTED] Transfer failed from %s: Target account is null.%n", accountNumber);
            return false;
        }
        System.out.printf("🔄 [INITIATING] Transferring $%.2f from %s to %s...%n",
                amount, this.accountNumber, targetAccount.getAccountNumber());

        if (this.withdraw(amount)) {
            targetAccount.deposit(amount);
            System.out.printf("✅ [TRANSFER COMPLETE] $%.2f successfully transferred from %s to %s.%n",
                    amount, this.accountNumber, targetAccount.getAccountNumber());
            return true;
        } else {
            System.out.printf("❌ [TRANSFER FAILED] Transfer aborted due to insufficient funds in %s.%n",
                    accountNumber);
            return false;
        }
    }

    // 6. Controlled Accessors
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public double getBalance() { return balance; }

    public void setAccountHolderName(String accountHolderName) {
        if (accountHolderName != null && !accountHolderName.isBlank()) {
            this.accountHolderName = accountHolderName;
        }
    }

    // 7. Display Summary
    public void displayAccountSummary() {
        System.out.printf("  Account No: %-10s | Holder: %-15s | Balance: $%.2f%n",
                accountNumber, accountHolderName, balance);
    }
}