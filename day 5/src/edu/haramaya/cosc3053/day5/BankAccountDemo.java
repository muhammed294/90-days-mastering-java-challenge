package edu.haramaya.cosc3053.day5;

public class BankAccountDemo {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("  HARAMAYA BANK: OOP ENCAPSULATION DEMO  ");
        System.out.println("=========================================\n");

        // 1. Valid Account Instantiations
        System.out.println("--- 1. CREATING VALID ACCOUNTS ---");
        BankAccount acc1 = new BankAccount("ACC-1001", "Abebe Bekele", 500.00);
        BankAccount acc2 = new BankAccount("ACC-1002", "Kebede Lemma", 200.00);
        acc1.displayAccountSummary();
        acc2.displayAccountSummary();

        // 2. Valid Financial Transactions
        System.out.println("\n--- 2. EXECUTING VALID TRANSACTIONS ---");
        acc1.deposit(150.00);
        acc1.withdraw(100.00);
        acc1.transferTo(acc2, 75.00);

        // 3. Testing Invalid Operations & Guards
        System.out.println("\n--- 3. TESTING EXCEPTION & BOUNDARY GUARDS ---");

        // Constructor Guard Test
        try {
            System.out.println("Attempting to open account with $10.00 deposit (Below $25.00 min):");
            BankAccount badAcc = new BankAccount("ACC-1003", "Invalid User", 10.00);
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught Expected Exception: " + e.getMessage());
        }

        // Negative Deposit Test
        System.out.println("\nAttempting negative deposit (-$50.00):");
        acc1.deposit(-50.00);

        // Overdraft / Minimum Balance Violation Test
        System.out.println("\nAttempting withdrawal ($260.00) leaving balance below $25.00 min:");
        acc2.withdraw(260.00);

        // 4. Final Account Summaries
        System.out.println("\n=========================================");
        System.out.println("         FINAL ACCOUNT SUMMARIES         ");
        System.out.println("=========================================");
        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        System.out.println("=========================================");
    }
}