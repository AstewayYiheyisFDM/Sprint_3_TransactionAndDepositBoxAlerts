package service;

import jakarta.mail.MessagingException;
import model.*;
import org.junit.Test;
import util.SampleDataUtil;

import java.util.List;

import static org.junit.Assert.*;

public class AlertServiceTest {
    AlertService alertService;
    List<Customer> customers;
    List<Account> accounts;
    SafetyDepositBox sb;

    public AlertServiceTest() {
        customers = SampleDataUtil.getSampleCustomers();
        sb = new SmallSafetyDepositBox(1);
        sb.setCustomer(customers.get(0));
        System.out.println(customers);
        accounts = SampleDataUtil.getSampleAccounts(customers);
        alertService = new AlertService(customers, accounts);
    }

    @Test
    public void test_sendTransactionAlert() throws MessagingException {
        alertService.sendTransactionAlert(customers.get(0).getCUSTOMER_ID(),
                accounts.get(0), 100.00, TransactionType.DEPOSIT);
    }

    @Test
    public void test_sendDepositBoxAlert(){
        alertService.sendDepositBoxAlert(sb, true); // for allocated
        alertService.sendDepositBoxAlert(sb, false); // for released
    }

    @Test
    public void test_generateTransactionAlertMessage(){
        Account account = accounts.get(0);
        String expectedMessage = String.format(
                "Hello %s,%n%nWe would like to notify you of a recent Deposit on your account.%n%n" +
                        "Transaction Details:%n- Amount: $%.2f%n- Account Number: %d%n- New Balance: $%.2f%n%n" +
                        "Thank you for banking with us.%n%nBest regards,%nYour Bank",
                customers.get(0).getName(),
                100.00,
                account.getACCOUNT_ID(),
                account.getBalance()
        );

        assertEquals(expectedMessage, alertService.generateTransactionAlertMessage(customers.get(0), accounts.get(0), 100.00, TransactionType.DEPOSIT));
    }

    @Test
    public void test_generateDepositBoxMessage(){
        Account account = accounts.get(0);
        String expectedMessage = String.format(
                "Hello %s,%n%nYour safety deposit box with ID %d has been allotted to you.%n%nThank you for banking with us.%nBest regards,%nYour Bank",
                sb.getCustomer().getName(),
                sb.getId()
        );

        assertEquals(expectedMessage, alertService.generateDepositBoxMessage(sb, true));

        String expectedMessageForNotAlloted = String.format(
                "Hello %s,%n%nYour safety deposit box with ID %d has been released.%n%nThank you for banking with us.%nBest regards,%nYour Bank",
                sb.getCustomer().getName(),
                sb.getId()
        );

        assertEquals(expectedMessageForNotAlloted, alertService.generateDepositBoxMessage(sb, false));
    }
}
