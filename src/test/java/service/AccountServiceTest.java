package service;

import DAO.AccountReaderDAO;
import DAO.AccountWriterDAO;
import model.Account;
import model.Company;
import model.Customer;
import model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
        List<Customer> sampleCustomers = getSampleCustomers();
        sampleAccount = new Account(100, getSampleCustomers().get(0));
    }

    private List<Customer> getSampleCustomers() {
        return Arrays.asList(
                new Person("John Doe", "123 Road", "johndoe@gmail.com"),
                new Person("Mark Alex", "456 Road", "ma@gmail.com"),
                new Company("Big Tech Company", "789 Road", "btc@gmail.com")
        );
    }

    private List<Account> getSampleAccounts(List<Customer> customers){
        return Arrays.asList(
                new Account(1500.00, customers.get(0)),
                new Account(2500.00, customers.get(1)),
                new Account(500.00, customers.get(2))
        );
    }

    @Test
    public void test_getAccounts_returnsListOfAccounts() {
        List<Account> accounts = Arrays.asList(sampleAccount, new Account(200, getSampleCustomers().get(1)), new Account(1000, getSampleCustomers().get(2)));
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
        Account account = new Account(10_000, getSampleCustomers().get(1));
        accountService.createAccount(account);

        Mockito.verify(accountWriterDAO).createAccount(account);
    }
}