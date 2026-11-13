package edu.haramaya.cosc3053.day6;

public class Day6ChallengeDemo {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  HARAMAYA BANK: DAY 6 OOP CHALLENGE DEMO TRACE  ");
        System.out.println("=================================================\n");

        // --- 1. BankAccount validation ---
        System.out.println("--- 1. TESTING BANKACCOUNT VALIDATION ---");
        BankAccount acc1 = new BankAccount("ACC-101", "Abebe Bekele", 500.00);
        System.out.printf("✅ [SUCCESS] Created Account %s (%s) with $%.2f%n",
                acc1.getAccountNumber(), acc1.getAccountHolder(), acc1.getBalance());
        BankAccount acc2 = new BankAccount("ACC-102", "Kebede Lemma", 200.00);
        System.out.printf("✅ [SUCCESS] Created Account %s (%s) with $%.2f%n",
                acc2.getAccountNumber(), acc2.getAccountHolder(), acc2.getBalance());

        acc1.deposit(150.00);
        acc1.withdraw(100.00);
        acc1.withdraw(700.00);   // overdraft -> rejected

        // --- 2. Atomic transfer ---
        System.out.println("\n--- 2. TESTING ATOMIC ACCOUNT TRANSFER SERVICE ---");
        AccountTransferService.transfer(acc1, acc2, 200.00);

        // --- 3. Portfolio ---
        System.out.println("\n--- 3. TESTING BANK PORTFOLIO BATCH INTEREST ---");
        BankPortfolio portfolio = new BankPortfolio(10);
        portfolio.addAccount(acc1);
        portfolio.addAccount(acc2);

        System.out.printf("Total Bank Liquidity : $%.2f%n", portfolio.getTotalLiquidity());
        BankAccount top = portfolio.getHighestBalanceAccount();
        System.out.printf("Highest Balance Account: %s (%s) with $%.2f%n",
                top.getAccountNumber(), top.getAccountHolder(), top.getBalance());

        System.out.println("Applying 5.0% Interest to Accounts with Balance >= $350.00...");
        int rewarded = portfolio.applyInterestToEligible(350.00, 5.0);
        System.out.println("Total Accounts Rewarded: " + rewarded);

        // --- Extra guard tests ---
        System.out.println("\n--- 4. EXTRA GUARD TESTS ---");
        AccountTransferService.transfer(acc1, acc1, 10.00);     // self-transfer
        AccountTransferService.transfer(acc1, null, 10.00);     // null target
        AccountTransferService.transfer(acc2, acc1, 99999.00);  // too much
        try {
            new BankAccount("ACC-103", "Bad User", -5.00);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }
        System.out.println("=================================================");
    }
}