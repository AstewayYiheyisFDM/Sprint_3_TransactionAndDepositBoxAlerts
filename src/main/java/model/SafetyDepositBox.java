package model;

public abstract class SafetyDepositBox {
    private boolean isAllotted;
    private long id;
    private Customer customer;

    public SafetyDepositBox(long id){
        this.id = id;
    }

    public boolean isAllotted(){
        return this.isAllotted;
    }

    public void setAllotted(boolean isAllotted){
        this.isAllotted = isAllotted;
    }

    public long getId(){
        return this.id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
