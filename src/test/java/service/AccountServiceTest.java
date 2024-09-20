package service;

import DAO.AccountReaderDAO;
import DAO.AccountWriterDAO;
import model.Account;
import model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import util.SampleDataUtil;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock
    AccountReaderDAO accountReaderDAO;

    @Mock
    AccountWriterDAO accountWriterDAO;

    @InjectMocks
    AccountServiceImpl accountService;

    Account sampleAccount;

    public AccountServiceTest() {
        List<Customer> sampleCustomers = SampleDataUtil.getSampleCustomers();
        sampleAccount = new Account(100, sampleCustomers.get(0));
    }

    @Test
    public void test_getAccounts_returnsListOfAccounts() {
        List<Account> accounts = Arrays.asList(sampleAccount, new Account(200, SampleDataUtil.getSampleCustomers().get(1)), new Account(1000, SampleDataUtil.getSampleCustomers().get(2)));
        Mockito.when(accountReaderDAO.readAccounts()).thenReturn(accounts);

        assertEquals(accounts, accountService.getAccounts());
    }

    @Test
    public void test_removeAccount_callsDAO_DeleteAccount() {
        accountService.removeAccount(sampleAccount);

        Mockito.verify(accountWriterDAO).deleteAccount(sampleAccount);
    }

    @Test
    public void test_createAccount_callsDao_createAccount() {
        Account account = new Account(10_000, SampleDataUtil.getSampleCustomers().get(1));
        accountService.createAccount(account);

        Mockito.verify(accountWriterDAO).createAccount(account);
    }
}