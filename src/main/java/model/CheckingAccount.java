package model;

import service.AccountService;
import service.AccountServiceImpl;
import service.AlertService;
import service.BankService;

public class CheckingAccount extends Account {
    private int nextCheckNumber;
    private static final double MIN_BALANCE = 100.00;

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

        if(currentAmount < MIN_BALANCE){
            BankService.notify(this, amount);
        }

        this.balance = currentAmount;

        return amount;
    }
}
