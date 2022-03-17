import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class SimpleCalculatorTest {
    @Test
    public void threePlusSevenShouldEqualFour(){
        var calculator = new SimpleCalculator();
        assertEquals(10,calculator.add(3,7));
    }
    @Test
    public void threePlusSevenShouldNotEqualEleven(){
        var calculator = new SimpleCalculator();
        assertNotEquals(11,calculator.add(3,7));
    }
    @Test
    public void fiveMinusThreeShouldEqualTwo(){
        var calculator = new SimpleCalculator();
        assertEquals(2,calculator.subtract(5,3));
    }
    @Test
    public void threeMultiplySevenShouldEqualTwentyOne(){
        var calculator = new SimpleCalculator();
        assertEquals(21,calculator.multiply(3,7));
    }
    @Test
    public void threeMultiplyEightShouldNotEqualSeven(){
        var calculator = new SimpleCalculator();
        assertNotEquals(7,calculator.multiply(3,8));
    }
    @Test
    public void twentyOneDivideBySevenShouldReturnThree(){
        var calculator = new SimpleCalculator();
        assertEquals(3,calculator.divide(21,7));
    }
    @Test
    public void divideByZeroException() {
        try {
            var calculator = new SimpleCalculator();
            calculator.divide(10, 0);
        }catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Can't divide by zero!");
        }
    }
    @Test
    public void theCalculatedAverageShouldBeSixAndAHalf(){
        var calculator = new SimpleCalculator();
        List<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(9);
        list.add(5);
        list.add(4);
        assertEquals(6.5,calculator.average(list));
    }
    @Test
    public void calculatedFactorialShouldReturn40320(){
        var calculator = new SimpleCalculator();
        assertEquals(40320,calculator.factorial(8));
    }
    @Test
    public void calculatedFactorialShouldNotReturn4(){
        var calculator = new SimpleCalculator();
        assertNotEquals(4,calculator.factorial(8));
    }

}