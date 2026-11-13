package edu.haramaya.cosc3053.day7;

import edu.haramaya.cosc3053.day7.TransferService.TransferResult;

public class RefactoringEngine {

    // Tiny test helper: stops the program with a clear message if a check fails
    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException("SELF-CHECK FAILED: " + message);
        }
    }

    private static boolean sameAmount(double a, double b) {
        return Math.abs(a - b) < 1e-9;
    }

    public static void main(String[] args) {
        // --- Setup ---
        AccountRepository repo = new AccountRepository();
        Account a1 = new Account("ACC-1", "Abebe Bekele", 500.00);
        Account a2 = new Account("ACC-2", "Kebede Lemma", 200.00);
        check(repo.add(a1) && repo.add(a2), "accounts should be added");
        check(!repo.add(a1), "duplicate account number must be rejected");
        check(!repo.add(null), "null account must be rejected");
        check(repo.size() == 2, "repository should hold 2 accounts");

        TransferService service = new TransferService(repo);

        // --- Valid transfer ---
        check(service.transfer("ACC-1", "ACC-2", 100.00) == TransferResult.SUCCESS, "valid transfer");
        check(sameAmount(a1.getBalance(), 400.00), "source balance after transfer");
        check(sameAmount(a2.getBalance(), 300.00), "target balance after transfer");

        // --- Invalid transfers: each must fail AND leave balances unchanged ---
        check(service.transfer("ACC-1", "ACC-1", 10) == TransferResult.SAME_ACCOUNT, "self transfer");
        check(service.transfer("ACC-1", "NOPE", 10) == TransferResult.TARGET_NOT_FOUND, "unknown target");
        check(service.transfer("NOPE", "ACC-2", 10) == TransferResult.SOURCE_NOT_FOUND, "unknown source");
        check(service.transfer("ACC-1", "ACC-2", -5) == TransferResult.INVALID_AMOUNT, "negative amount");
        check(service.transfer("ACC-1", "ACC-2", Double.NaN) == TransferResult.INVALID_AMOUNT, "NaN amount");
        check(service.transfer("ACC-1", "ACC-2", 1000) == TransferResult.INSUFFICIENT_FUNDS, "too much");
        check(sameAmount(a1.getBalance(), 400.00), "source unchanged after failures");
        check(sameAmount(a2.getBalance(), 300.00), "target unchanged after failures");

        // --- Constructor guards ---
        boolean threw = false;
        try {
            new Account("ACC-3", "  ", 50.00);
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        check(threw, "blank holder name must throw");

        // All checks passed: print the expected output from the worksheet
        System.out.println("=== DAY 07 WEEKLY REFACTORING & CATCH-UP COMPLETED ===");
    }
}