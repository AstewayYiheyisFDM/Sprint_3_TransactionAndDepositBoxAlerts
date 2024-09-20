package model;

import jakarta.mail.MessagingException;
import service.AlertService;

public class CheckingAccount extends Account {
    private int nextCheckNumber;
    private static final double MIN_BALANCE = 100.00;
    AlertService alertService;

    public CheckingAccount(double balance, Customer customer){
        // it will start at 1 since the first call to getNextCheckNumber will return 1
        super(balance, customer);
        this.nextCheckNumber = 0;
        alertService = new AlertService();
    }

    public int getNextCheckNumber() {
        return ++nextCheckNumber;
    }

    public void setNextCheckNumber(int nextCheckNumber) {
        this.nextCheckNumber = nextCheckNumber;
    }

    @Override
    public double withdraw(double amount) throws MessagingException {
        double currentAmount = this.balance - amount;

        if(currentAmount < MIN_BALANCE){
            alertService.sendTransactionAlert(this.getCustomer().getCUSTOMER_ID(),this, amount);
        }

        this.balance = currentAmount;

        return amount;
    }
}
