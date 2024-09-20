package service;

import jakarta.mail.MessagingException;
import model.Account;
import model.TransactionType;

public class BankService {
    private AlertService alertService;

    public BankService(AlertService alertService){
        this.alertService = alertService;
    }

    public void deposit(Account account, double amount) {
        account.deposit(amount);

        notifyTransaction(account, amount, TransactionType.DEPOSIT);
    }

    public void withdraw(Account account, double amount) throws MessagingException {
        account.withdraw(amount);

        notifyTransaction(account, amount, TransactionType.WITHDRAW);
    }

    public void notifyTransaction(Account account, double amount, TransactionType transactionType) {
        try{
            alertService.sendTransactionAlert(account.getCustomer().getCUSTOMER_ID(), account, amount, transactionType);
        }
        catch(MessagingException messagingException){
            messagingException.printStackTrace();
        }
    }
}
