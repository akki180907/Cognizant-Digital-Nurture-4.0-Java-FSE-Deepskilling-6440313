package com.junitexercises;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class CalculatorFixtureTest {

    private Calculator calc;

    @Before
    public void setUp() {
        // Arrange
        calc = new Calculator();
        System.out.println("Setup complete");
    }

    @After
    public void tearDown() {
        // Cleanup
        calc = null;
        System.out.println("Teardown complete");
    }

    @Test
    public void testAddition() {
        // Act
        int result = calc.add(10, 5);

        // Assert
        assertEquals(15, result);
    }

    @Test
    public void testSubtraction() {
        int result = calc.subtract(9, 4);
        assertEquals(5, result);
    }
}
