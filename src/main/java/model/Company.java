package model;

import jakarta.mail.MessagingException;

public class Company extends Customer{
    public Company(String name, String address, String emailAddress) {
        super(name, address, emailAddress);
    }

    @Override
    public void chargeAllAccounts(double amount){
        this.getAccounts().forEach(a -> {
            if(a instanceof CheckingAccount){
                try {
                    a.withdraw(amount);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
            else if(a instanceof SavingsAccount){
                try {
                    a.withdraw(amount * 2);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
