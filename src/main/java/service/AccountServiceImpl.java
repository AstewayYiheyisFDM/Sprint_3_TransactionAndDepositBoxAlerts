package service;

import DAO.AccountReaderDAO;
import DAO.AccountWriterDAO;
import model.Account;

import java.util.List;

public class AccountServiceImpl implements AccountService{
    private AccountReaderDAO accountReaderDAO;
    private AccountWriterDAO accountWriterDAO;

    public AccountServiceImpl(final AccountReaderDAO accountReaderDAO, final AccountWriterDAO accountWriterDAO){
        this.accountReaderDAO = accountReaderDAO;
        this.accountWriterDAO = accountWriterDAO;
    }

    @Override
    public Account createAccount(Account account) {
        return accountWriterDAO.createAccount(account);
    }

    @Override
    public void removeAccount(Account account) {
        accountWriterDAO.deleteAccount(account);
    }

    @Override
    public List<Account> getAccounts() {
        return accountReaderDAO.readAccounts();
    }
}
