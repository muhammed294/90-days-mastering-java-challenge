package edu.haramaya.cosc3053.day6;

public class BankPortfolio {

    private BankAccount[] accounts;
    private int count;

    public BankPortfolio(int capacity) {
        this.accounts = new BankAccount[capacity];
        this.count = 0;
    }

    // 3.1 Add account if not null and there is free space
    public boolean addAccount(BankAccount account) {
        if (account == null || count >= accounts.length) {
            return false;
        }
        accounts[count] = account;
        count++;
        return true;
    }

    // 3.2 Sum of all balances
    public double getTotalLiquidity() {
        double total = 0.0;
        for (int i = 0; i < count; i++) {
            if (accounts[i] != null) {
                total += accounts[i].getBalance();
            }
        }
        return total;
    }

    // 3.3 Account with the maximum balance (null if portfolio is empty)
    public BankAccount getHighestBalanceAccount() {
        BankAccount highest = null;
        for (int i = 0; i < count; i++) {
            if (accounts[i] == null) continue;
            if (highest == null || accounts[i].getBalance() > highest.getBalance()) {
                highest = accounts[i];
            }
        }
        return highest;
    }

    // 3.4 Add interest to every account with balance >= threshold
    public int applyInterestToEligible(double minThreshold, double interestRatePercent) {
        int rewarded = 0;
        for (int i = 0; i < count; i++) {
            BankAccount acc = accounts[i];
            if (acc != null && acc.getBalance() >= minThreshold) {
                double interest = acc.getBalance() * interestRatePercent / 100.0;
                if (acc.deposit(interest)) {
                    rewarded++;
                }
            }
        }
        return rewarded;
    }
}