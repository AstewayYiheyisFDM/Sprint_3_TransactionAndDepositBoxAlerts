package service;

import jakarta.mail.MessagingException;
import model.Account;
import model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BankServiceTest{
    @Mock
    AlertService alertService;
    @InjectMocks
    BankService bankService;

    Account account;
    Person p;

    public BankServiceTest(){
        p = new Person("John Doe", "123 road", "johndoe@gmail.com");
        account = new Account(1_000, p);
    }

    @Test
    public void test_when_deposit_isCalled_sendTransactionAlert_isCalled() throws MessagingException {
        bankService.withdraw(account, 100.00);
        Mockito.verify(alertService).sendTransactionAlert(p.getCUSTOMER_ID(), account, 100.00);
    }

    @Test
    public void test_when_withdraw_isCalled_sendTransactionAlert_isCalled() throws MessagingException {
        bankService.deposit(account, 300.00);
        Mockito.verify(alertService).sendTransactionAlert(p.getCUSTOMER_ID(), account, 300.00);
    }
}
