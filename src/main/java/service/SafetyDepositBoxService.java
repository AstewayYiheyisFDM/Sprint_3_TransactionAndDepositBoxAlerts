package service;

import model.SafetyDepositBox;
import model.SmallSafetyDepositBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SafetyDepositBoxService {
    private static SafetyDepositBoxService safetyDepositBoxService;
    private List<SafetyDepositBox> safetyDepositBoxes;
    private static int numberOfSafetyDepositBoxes = 2;

    private SafetyDepositBoxService(){
        safetyDepositBoxes = new ArrayList<>();

        for(int i = 0; i < numberOfSafetyDepositBoxes; i++){
            SmallSafetyDepositBox sb = new SmallSafetyDepositBox();
            sb.setId(i);
            sb.setAllotted(false);
            safetyDepositBoxes.add(sb);
        }
    }

    public static SafetyDepositBoxService getInstance(){
        if(safetyDepositBoxService == null){
            safetyDepositBoxService = new SafetyDepositBoxService();
        }

        return safetyDepositBoxService;
    }

    public static int getNumberOfSafetyDepositBoxes() {
        return numberOfSafetyDepositBoxes;
    }

    public static synchronized void setNumberOfSafetyDepositBoxes(int numberOfSafetyDepositBoxes) {
        SafetyDepositBoxService.numberOfSafetyDepositBoxes = numberOfSafetyDepositBoxes;
    }

    public synchronized SafetyDepositBox allocateSafetyDepositBox() throws InterruptedException {
        Optional<SafetyDepositBox> safetyDepositBox = getReleasedSafetyDepositBox();
        boolean isWaiting = false; // just for testing purposes

        if(safetyDepositBox.isPresent()){
            SafetyDepositBox sb = safetyDepositBox.get();
            sb.setAllotted(true);

            return sb;
        }
        else{
            // if the limit has not been reached, create a new safety deposit box and return it
            if(numberOfSafetyDepositBoxes > safetyDepositBoxes.size()){
                SafetyDepositBox sb = new SmallSafetyDepositBox();
                sb.setAllotted(true);
                safetyDepositBoxes.add(sb);

                return sb;
            }
            else{
                isWaiting = true;
                wait();
                isWaiting = false;

                return getReleasedSafetyDepositBox().orElseThrow(() -> new IllegalStateException("No Boxes found!"));
            }
        }
    }

    public synchronized void releaseSafetyDepositBox(SafetyDepositBox safetyDepositBox){
        for(SafetyDepositBox box:safetyDepositBoxes){
            if(box.equals(safetyDepositBox)){
                box.setAllotted(false);
            }
        }

        notify();
        // safetyDepositBoxes.remove(safetyDepositBox);
    }

    public int getNumberOfAvailableSafetyDepositBoxes(){
        int numOfAvailableBoxes = 0;

        for(SafetyDepositBox box:safetyDepositBoxes){
            if(!box.isAllotted()){
                numOfAvailableBoxes++;
            }
        }

        return numOfAvailableBoxes;
    }

    public Optional<SafetyDepositBox> getReleasedSafetyDepositBox(){
        for(SafetyDepositBox box:safetyDepositBoxes){
            if(!box.isAllotted()){
                return Optional.of(box);
            }
        }

        return null;
    }

    public List<SafetyDepositBox> getSafetyDepositBoxes() {
        return safetyDepositBoxes;
    }


}
