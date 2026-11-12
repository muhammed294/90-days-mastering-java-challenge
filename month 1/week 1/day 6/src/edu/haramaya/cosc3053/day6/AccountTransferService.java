package edu.haramaya.cosc3053.day6;

public class AccountTransferService {

    // 2.1 Transfer: withdraw first, deposit only if withdraw succeeded
    public static boolean transfer(BankAccount fromAcc, BankAccount toAcc, double amount) {
        if (fromAcc == null || toAcc == null) {
            System.out.println("❌ [TRANSFER FAILED] Source or target account is null.");
            return false;
        }
        if (fromAcc == toAcc || fromAcc.getAccountNumber().equals(toAcc.getAccountNumber())) {
            System.out.println("❌ [TRANSFER FAILED] Cannot transfer to the same account.");
            return false;
        }
        if (amount <= 0.0) {
            System.out.println("❌ [TRANSFER FAILED] Amount must be positive.");
            return false;
        }

        System.out.printf("🔄 [TRANSFER] Initiating $%.2f transfer from %s to %s...%n",
                amount, fromAcc.getAccountNumber(), toAcc.getAccountNumber());

        if (fromAcc.withdraw(amount)) {
            toAcc.deposit(amount);
            System.out.printf("✅ [TRANSFER COMPLETE] Transfer of $%.2f from %s to %s Succeeded!%n",
                    amount, fromAcc.getAccountNumber(), toAcc.getAccountNumber());
            return true;
        } else {
            System.out.println("❌ [TRANSFER ABORTED] Withdrawal failed, no money was moved.");
            return false;
        }
    }
}