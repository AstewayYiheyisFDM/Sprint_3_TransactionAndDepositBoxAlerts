package service;

import jakarta.mail.MessagingException;
import model.Account;

public class BankService {
    private AlertService alertService;

    public BankService(AlertService alertService){
        this.alertService = alertService;
    }

    public void deposit(Account account, double amount) throws MessagingException {
        account.deposit(amount);

        alertService.sendTransactionAlert(account.getCustomer().getCUSTOMER_ID(), account, amount);
    }

    public void withdraw(Account account, double amount) throws MessagingException {
        account.withdraw(amount);

        alertService.sendTransactionAlert(account.getCustomer().getCUSTOMER_ID(), account, amount);
    }
}
