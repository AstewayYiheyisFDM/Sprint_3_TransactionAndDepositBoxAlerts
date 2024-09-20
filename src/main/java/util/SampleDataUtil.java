package util;

import model.Account;
import model.Company;
import model.Customer;
import model.Person;

import java.util.Arrays;
import java.util.List;

public class SampleDataUtil {
    private SampleDataUtil(){
    };

    public static List<Customer> getSampleCustomers() {
        return Arrays.asList(
                new Person("John Doe", "123 Road", "johndoe@gmail.com"),
                new Person("Mark Alex", "456 Road", "ma@gmail.com"),
                new Company("Big Tech Company", "789 Road", "btc@gmail.com")
        );
    }

    public static List<Account> getSampleAccounts(List<Customer> customers){
        return Arrays.asList(
                new Account(1500.00, customers.get(0)),
                new Account(2500.00, customers.get(1)),
                new Account(500.00, customers.get(2))
        );
    }
}
