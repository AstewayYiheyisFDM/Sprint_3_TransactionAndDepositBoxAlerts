package model;

public class Company extends Customer{
    public Company(String name, String address, String emailAddress) {
        super(name, address, emailAddress);
    }

    @Override
    public void chargeAllAccounts(double amount){
        this.getAccounts().forEach(a -> {
            if(a instanceof CheckingAccount){
                a.withdraw(amount);
            }
            else if(a instanceof SavingsAccount){
                a.withdraw(amount * 2);
            }
        });
    }
}
