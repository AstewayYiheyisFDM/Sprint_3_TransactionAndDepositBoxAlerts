package service;

import jakarta.mail.MessagingException;
import model.Account;
import model.Customer;
import model.SafetyDepositBox;
import util.EmailUtil;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class AlertService {
    List<Account> accounts;
    List<Customer> customers;

    public AlertService(){
    }

    public AlertService(List<Customer> customers, List<Account> accounts) {
        this.accounts = accounts;
        this.customers = customers;
    }

    public void sendTransactionAlert(Long customerId, Account account, double amount) throws MessagingException {
        Optional<Customer> customer = customers.stream().filter(c -> c.getCUSTOMER_ID() == customerId).findFirst();

        if (customer.isPresent()) {
            Customer c = customer.get();
            try {
                EmailUtil.sendEmail(c.getEmailAddress(), "Transaction Alert!",
                        generateTransactionAlertMessage(c, account, amount));
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendDepositBoxAlert(SafetyDepositBox sb, boolean isAlloted) {
        try {
            EmailUtil.sendEmail(sb.getCustomer().getEmailAddress(), "Deposit Box Alert!",
                    generateDepositBoxMessage(sb, isAlloted));
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    public String generateTransactionAlertMessage(Customer customer, Account account, double amount) {
        return String.format(
                "Hello %s,%n%nWe would like to notify you of a recent transaction on your account.%n%n" +
                        "Transaction Details:%n- Amount: $%.2f%n- Account Number: %d%n- New Balance: $%.2f%n%n" +
                        "Thank you for banking with us.%n%nBest regards,%nYour Bank",
                customer.getName(),
                amount,
                account.getACCOUNT_ID(),
                account.getBalance()
        );
    }

    public String generateDepositBoxMessage(SafetyDepositBox sb, boolean isAlloted) {
        Supplier<String> supplier;
        if (isAlloted) {
            supplier = () -> String.format(
                    "Hello %s,%n%nYour safety deposit box with ID %d has been allotted to you.%n%nThank you for banking with us.%nBest regards,%nYour Bank",
                    sb.getCustomer().getName(),
                    sb.getId()
            );
        } else {
            supplier = () -> String.format(
                    "Hello %s,%n%nYour safety deposit box with ID %d has been released.%n%nThank you for banking with us.%nBest regards,%nYour Bank",
                    sb.getCustomer().getName(),
                    sb.getId()
            );
        }

        return supplier.get();
    }
}
