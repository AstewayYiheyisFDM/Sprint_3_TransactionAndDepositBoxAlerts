package model;

public abstract class SafetyDepositBox {
    private boolean isAllotted;
    private double id;

    public boolean isAllotted(){
        return false;
    }

    public void setAllotted(boolean isAllotted){
        this.isAllotted = isAllotted;
    }

    public double getId(){
        return this.id;
    }

    public void setId(double id){
        this.id = id;
    }
}
