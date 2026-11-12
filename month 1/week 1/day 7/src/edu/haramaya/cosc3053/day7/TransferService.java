package edu.haramaya.cosc3053.day7;

import java.util.Optional;

public class TransferService {

    public enum TransferResult {
        SUCCESS,
        SOURCE_NOT_FOUND,
        TARGET_NOT_FOUND,
        SAME_ACCOUNT,
        INVALID_AMOUNT,
        INSUFFICIENT_FUNDS
    }

    private final AccountRepository repository;

    public TransferService(AccountRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null.");
        }
        this.repository = repository;
    }

    public TransferResult transfer(String fromNumber, String toNumber, double amount) {
        Optional<Account> from = repository.findByNumber(fromNumber);
        if (from.isEmpty()) return TransferResult.SOURCE_NOT_FOUND;

        Optional<Account> to = repository.findByNumber(toNumber);
        if (to.isEmpty()) return TransferResult.TARGET_NOT_FOUND;

        if (fromNumber.equals(toNumber)) return TransferResult.SAME_ACCOUNT;

        if (!(amount > 0.0) || !Double.isFinite(amount)) return TransferResult.INVALID_AMOUNT;

        // Withdraw FIRST. If it fails, nothing has changed.
        if (!from.get().withdraw(amount)) return TransferResult.INSUFFICIENT_FUNDS;

        // Rollback safety: if the deposit fails, give the money back.
        if (!to.get().deposit(amount)) {
            from.get().deposit(amount);
            return TransferResult.INVALID_AMOUNT;
        }
        return TransferResult.SUCCESS;
    }
}