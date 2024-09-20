package model;

public class SmallSafetyDepositBox extends SafetyDepositBox{
    public SmallSafetyDepositBox(long id){
        super(id);
    }

    private double capacity;

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
}
