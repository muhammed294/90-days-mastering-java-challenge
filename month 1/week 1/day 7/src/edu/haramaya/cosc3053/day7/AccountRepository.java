package edu.haramaya.cosc3053.day7;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountRepository {

    private final Map<String, Account> accounts = new HashMap<>();

    /** Returns false if the account is null or the number already exists. */
    public boolean add(Account account) {
        if (account == null || accounts.containsKey(account.getAccountNumber())) {
            return false;
        }
        accounts.put(account.getAccountNumber(), account);
        return true;
    }

    /** Replaces the old "legacy lookup" loop. */
    public Optional<Account> findByNumber(String accountNumber) {
        if (accountNumber == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(accounts.get(accountNumber));
    }

    public int size() {
        return accounts.size();
    }
}