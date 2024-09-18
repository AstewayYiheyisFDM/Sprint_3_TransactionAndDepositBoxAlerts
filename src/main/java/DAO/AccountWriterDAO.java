package DAO;

import model.Account;

public interface AccountWriterDAO {
    Account createAccount(Account account);
    void deleteAccount(Account account);
}
