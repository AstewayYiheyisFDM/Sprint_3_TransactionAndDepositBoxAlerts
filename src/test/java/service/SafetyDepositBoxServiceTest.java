package service;

import model.SafetyDepositBox;
import org.junit.Before;
import org.junit.Test;
import util.SampleDataUtil;

import static org.junit.Assert.assertEquals;

public class SafetyDepositBoxServiceTest {
    private SafetyDepositBoxService service;

    @Before
    public void setUp() {
        service = SafetyDepositBoxService.getInstance();
    }

    @Test
    public void testAllocateAndReleaseBoxes() throws InterruptedException {
        // Test1: Two threads requesting and releasing boxes
        Thread t1 = new Thread(() -> {
            try {
                SafetyDepositBox box = service.allocateSafetyDepositBox(SampleDataUtil.getSampleCustomers().get(0));
                Thread.sleep(5000);  // Hold the box for 5 seconds
                service.releaseSafetyDepositBox(box);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                SafetyDepositBox box = service.allocateSafetyDepositBox(SampleDataUtil.getSampleCustomers().get(1));
                Thread.sleep(5000);  // Hold the box for 5 seconds
                service.releaseSafetyDepositBox(box);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Verify no thread was kept waiting
        assertEquals(2, service.getNumberOfAvailableSafetyDepositBoxes());
    }

    @Test
    public void testWaitingForBox() throws InterruptedException {
        // Test2: Three threads requesting and releasing boxes
        Thread t1 = new Thread(() -> {
            try {
                SafetyDepositBox box = service.allocateSafetyDepositBox(SampleDataUtil.getSampleCustomers().get(2));
                Thread.sleep(5000);  // Hold the box for 5 seconds
                service.releaseSafetyDepositBox(box);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                SafetyDepositBox box = service.allocateSafetyDepositBox(SampleDataUtil.getSampleCustomers().get(0));
                Thread.sleep(5000);  // Hold the box for 5 seconds
                service.releaseSafetyDepositBox(box);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                SafetyDepositBox box = service.allocateSafetyDepositBox(SampleDataUtil.getSampleCustomers().get(1));
                Thread.sleep(5000);  // Hold the box for 5 seconds
                service.releaseSafetyDepositBox(box);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        // Verify that all threads completed without exception
        assertEquals(2, service.getNumberOfAvailableSafetyDepositBoxes());
    }
}
