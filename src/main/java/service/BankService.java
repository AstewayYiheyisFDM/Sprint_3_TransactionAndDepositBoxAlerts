package service;

import jakarta.mail.MessagingException;
import model.Account;

public class BankService {
    private static AlertService alertService;

    public BankService(AlertService alertService){
        this.alertService = alertService;
    }

    public void deposit(Account account, double amount) {
        account.deposit(amount);

        BankService.notify(account, amount);
    }

    public void withdraw(Account account, double amount) {
        account.withdraw(amount);

        BankService.notify(account, amount);
    }

    public static void notify(Account account, double amount) {
        try{
            alertService.sendTransactionAlert(account.getCustomer().getCUSTOMER_ID(), account, amount);
        }
        catch(MessagingException messagingException){
            messagingException.printStackTrace();
        }
    }
}
