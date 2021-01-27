package ru.progwards.java2.lessons.annotation;

import ru.progwards.java2.lessons.tests.calc.SimpleCalculator;

public class CalculatorTest {
    SimpleCalculator calculator;

    @Before
    void createObject(){
        calculator = new SimpleCalculator();
    }

    @After
    void deleteObject() {
        calculator = null;
    }

    @Test(priority = 2)
    boolean testSum1() {
        return calculator.sum(3,6) == 8;
    }

    @Test(priority = 1)
    boolean testSum2() {
        return calculator.diff(15,5) == 10;
    }

    public static void main(String[] args) {
        CalculatorTest calculatorTest = new CalculatorTest();
        System.out.println(calculatorTest.getClass());
        calculatorTest.createObject();
        System.out.println(calculatorTest.testSum1());
        System.out.println(calculatorTest.testSum2());
    }
}
