package service;

import model.Account;

import java.util.List;

public interface AccountService {
    public Account createAccount(Account account);

    public void removeAccount(Account account);

    public List<Account> getAccounts();
}
