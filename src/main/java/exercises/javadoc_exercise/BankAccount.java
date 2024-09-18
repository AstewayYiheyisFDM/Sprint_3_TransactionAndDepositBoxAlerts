package exercises.javadoc_exercise;

/**
 * Represents a bank account that allows financial operations
 * such as depositing and withdrawing funds.
 *
 * @author Asteway Yiheyis
 * @version 1.0
 */
public class BankAccount {
    private int accountNumber;
    private String accountName;
    private double balance;

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Adds a specified amount to the current balance of the bank account.
     *
     * @param amount the amount to be deposited into the account
     * @throws InvalidAccountException if the deposit amount is negative
     * @see InvalidAccountException
     */
    public void deposit(double amount) throws InvalidAccountException {
        if (amount < 0) {
            throw new InvalidAccountException("Deposit amount cannot be negative.");
        }
        balance += amount;
    }

    /**
     * Deducts a specified amount from the current balance of the bank account.
     *
     * @param amount the amount to withdraw from the account
     * @throws InvalidAccountException if the withdrawal amount exceeds the
     *         available balance or is negative
     * @see InvalidAccountException
     */
    public void withdraw(double amount) throws InvalidAccountException {
        if (amount < 0) {
            throw new InvalidAccountException("Withdrawal amount cannot be negative.");
        }
        if (balance < amount) {
            throw new InvalidAccountException("Insufficient funds for withdrawal.");
        }
        balance -= amount;
    }
}
