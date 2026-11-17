package edu.haramaya.cosc3053.day6;

public class BankAccountTransfer {
    // BankAccount class from Day 5 assumed available

    // TODO 6.1: static transfer(BankAccount src, BankAccount dest, double amount)
    public static boolean transfer(BankAccount src, BankAccount dest, double amount) {
        if (src == null || dest == null || src == dest || amount <= 0.0) {
            return false;
        }
        if (src.withdraw(amount)) {
            dest.deposit(amount);
            return true;
        }
        return false;
    }

    public static BankAccount findAccount(BankAccount[] accounts, String accountNumber) {
        if (accounts == null || accountNumber == null || accountNumber.isBlank()) {
            return null;
        }
        for (BankAccount account : accounts) {
            if (account != null && account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public static boolean transferByNumber(BankAccount[] accounts, String fromNumber, String toNumber, double amount) {
        BankAccount src = findAccount(accounts, fromNumber);
        BankAccount dest = findAccount(accounts, toNumber);

        if (src == null || dest == null || src == dest || amount <= 0.0) {
            return false;
        }
        if (src.withdraw(amount)) {
            dest.deposit(amount);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("ACC-101", "Abebe", 500.0);
        BankAccount acc2 = new BankAccount("ACC-102", "Kebede", 200.0);

        boolean success = transfer(acc1, acc2, 150.0);
        System.out.println("Transfer Success: " + success);
        System.out.println("ACC-101 Balance: $" + acc1.getBalance());
        System.out.println("ACC-102 Balance: $" + acc2.getBalance());
    }
}