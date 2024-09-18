package model;

import jakarta.mail.MessagingException;

public class Person extends Customer{
    public Person(String name, String address, String emailAddress) {
        super(name, address, emailAddress);
    }

    @Override
    public void chargeAllAccounts(double amount){
        this.getAccounts().forEach(a -> {
            try {
                a.withdraw(amount);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }
}
