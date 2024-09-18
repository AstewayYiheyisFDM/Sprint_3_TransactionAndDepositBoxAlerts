package DAO;

import model.Account;

import java.util.List;

public interface AccountReaderDAO {
    List<Account> readAccounts();
}
