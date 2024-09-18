package model;

public class Person extends Customer{
    public Person(String name, String address, String emailAddress) {
        super(name, address, emailAddress);
    }

    @Override
    public void chargeAllAccounts(double amount){
        this.getAccounts().forEach(a -> a.withdraw(amount));
    }
}
