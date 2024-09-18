package service;

import jakarta.mail.MessagingException;
import model.Account;

public class BankService {
    private AlertService alertService;

    public BankService(AlertService alertService){
        this.alertService = alertService;
    }

    public void deposit(Account account, double amount) {
        account.deposit(amount);

        notifyTransaction(account, amount);
    }

    public void withdraw(Account account, double amount) throws MessagingException {
        account.withdraw(amount);

        notifyTransaction(account, amount);
    }

    public void notifyTransaction(Account account, double amount) {
        try{
            alertService.sendTransactionAlert(account.getCustomer().getCUSTOMER_ID(), account, amount);
        }
        catch(MessagingException messagingException){
            messagingException.printStackTrace();
        }
    }
}
