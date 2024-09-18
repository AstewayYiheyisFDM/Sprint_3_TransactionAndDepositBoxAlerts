package model;

public class SavingsAccount extends Account{
    private double interestRate;

    public SavingsAccount(double balance, Customer customer) {
        super(balance, customer);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public double withdraw(double amount){
        if(balance - amount < 0){
            // for anticipated future changes
//            throw new OverLimitWithdrawalException("Withdraw Amount is over the current balance!");
            return 0;
        }

        this.balance -= amount;
        this.setBalance(balance);

        return amount;
    }

    public void addInterest(){
        double interestDue = this.balance * interestRate / 100;

        this.setBalance(balance + interestDue);
    }
}
