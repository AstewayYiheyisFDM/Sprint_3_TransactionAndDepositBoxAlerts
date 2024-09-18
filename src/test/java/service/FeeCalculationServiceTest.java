package service;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FeeCalculationServiceTest {
    FeeCalculatorService feeCalculatorService;

    @Before
    public void initialize() {
        feeCalculatorService = new FeeCalculatorServiceImpl();
    }

    @Test
    public void testCalculateFee_LessThanOrEqualTo100() {
        // test for balance <= $100
        assertEquals("Test failed for balance <= 100", 20.00, feeCalculatorService.calculateFee(50));
        assertEquals("Test failed for balance <= 100", 20.00, feeCalculatorService.calculateFee(100));
    }

    @Test
    public void testCalculateFee_GreaterThan100_LessThanOrEqualTo500() {
        // test for balance > $100 &&  <= $500
        assertEquals("Test failed for balance > 100 && <= 500", 15.00, feeCalculatorService.calculateFee(300));
        assertEquals("Test failed for balance > 100 && <= 500", 15.00, feeCalculatorService.calculateFee(500));
    }

    @Test
    public void testCalculateFee_GreaterThan500_LessThanOrEqualTo1000() {
        // test for balance > $500 &&  <= $1000
        assertEquals("Test failed for balance > 500 && <= 1000", 10.00, feeCalculatorService.calculateFee(700));
        assertEquals("Test failed for balance > 500 && <= 1000", 10.00, feeCalculatorService.calculateFee(1000));
    }

    @Test
    public void testCalculateFee_GreaterThan1000_LessThanOrEqualTo2000() {
        // test for balance > $1000 && <= $2000
        assertEquals("Test failed for balance > 1000 && <= 2000", 5.00, feeCalculatorService.calculateFee(1200));
        assertEquals("Test failed for balance > 1000 && <= 2000", 5.00, feeCalculatorService.calculateFee(2000));
    }

    @Test
    public void testCalculateFee_GreaterThan2000() {
        // test for balance > $2000
        assertEquals("Test failed for balance > 2000", 0.00, feeCalculatorService.calculateFee(5000));
    }
}
