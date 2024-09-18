package model;

import service.AccountService;
import service.AccountServiceImpl;
import service.AlertService;

public class CheckingAccount extends Account {
    private int nextCheckNumber;
    private static final double MIN_BALANCE = 100.00;
    AccountService accountService;

    public CheckingAccount(double balance, Customer customer){
        // it will start at 1 since the first call to getNextCheckNumber will return 1
        super(balance, customer);
        this.nextCheckNumber = 0;
    }

    public int getNextCheckNumber() {
        return ++nextCheckNumber;
    }

    public void setNextCheckNumber(int nextCheckNumber) {
        this.nextCheckNumber = nextCheckNumber;
    }

    @Override
    public double withdraw(double amount){
        double currentAmount = this.balance - amount;
        accountService = new AccountServiceImpl(new Ac);

        if(currentAmount < MIN_BALANCE){
            AlertService alertService = new AlertService();
        }

        this.balance -= amount;

        return amount;
    }
}
