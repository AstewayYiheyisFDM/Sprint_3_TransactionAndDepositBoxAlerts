package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Customer {
    private static long nextCustomerId = 2_000_000;
    private final long CUSTOMER_ID;
    private String name;
    private String address;
    private String emailAddress;

    private List<Account> accounts;

    Customer(final String name, final String address, final String emailAddress){
        nextCustomerId += 7;
        this.CUSTOMER_ID = nextCustomerId;
        this.name = name;
        this.address = address;
        accounts = new ArrayList<>();
        this.emailAddress = emailAddress;
    }

    public long getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account){
        this.accounts.add(account);
    }

    public void removeAccount(Account account){
        this.accounts.remove(account);
    }

    public abstract void chargeAllAccounts(double amount);

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
