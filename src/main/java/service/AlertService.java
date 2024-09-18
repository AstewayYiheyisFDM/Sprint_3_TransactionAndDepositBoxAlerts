package service;

import jakarta.mail.MessagingException;
import model.Account;
import model.Customer;
import util.EmailUtil;

import java.util.List;
import java.util.Optional;

public class AlertService {
    List<Account> accounts;
    List<Customer> customers;

    public AlertService(List<Customer> customers, List<Account> accounts){
        this.accounts = accounts;
        this.customers = customers;
    }

    public void sendTransactionAlert(Long customerId, Account account, double amount) throws MessagingException {
        Optional<Customer> customer = customers.stream().filter(c -> c.getCUSTOMER_ID() == customerId).findFirst();

        if(customer.isPresent()){
            Customer c = customer.get();
            try{
                EmailUtil.sendEmail(c.getEmailAddress(), "Transaction Alert!",
                        generateAlertMessage(c, account, amount));
            }
            catch(MessagingException ex){
                ex.printStackTrace();
            }
        }
    }

    private String generateAlertMessage(Customer customer, Account account, double amount){
        return String.format(
                "Hello %s,\n\nWe would like to notify you of a recent transaction on your account.\n\n" +
                        "Transaction Details:\n- Amount: $%.2f\n- Account Number: %d\n- New Balance: $%.2f\n\n" +
                        "Thank you for banking with us.\nBest regards,\nYour Bank",
                customer.getName(),
                amount,
                account.getACCOUNT_ID(),
                account.getBalance()
        );
    }
}
