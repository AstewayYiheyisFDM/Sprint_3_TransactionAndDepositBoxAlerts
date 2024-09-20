package model;

import jakarta.mail.MessagingException;

public class Account {
    private static long nextAccountId = 1_000;
    private final long ACCOUNT_ID;
    private Customer customer;
    protected double balance;

    public Account(double balance, final Customer customer) {
        nextAccountId += 5;
        this.ACCOUNT_ID = nextAccountId;
        this.balance = balance;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount){
        this.balance += amount;
    }

    public double withdraw(double amount) throws MessagingException {
        this.balance -= amount;

        return amount;
    }

    public void correctBalance(double amount) {
        this.balance = amount;
    }

    public long getACCOUNT_ID() {
        return ACCOUNT_ID;
    }
}
