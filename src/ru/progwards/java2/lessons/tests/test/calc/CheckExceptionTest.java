package ru.progwards.java2.lessons.tests.test.calc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ru.progwards.java2.lessons.tests.calc.IntOverflowException;
import ru.progwards.java2.lessons.tests.calc.SimpleCalculator;

public class CheckExceptionTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Test
    public void SumWithException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Переполнение диапазона int при сложении "
                +((long)Integer.MAX_VALUE + 1));
        SimpleCalculator.sum(Integer.MAX_VALUE, 1);
    }
    @Test
    public void DiffWithException() {
        exception.expect(IntOverflowException.class);
        exception.expectMessage("Переполнение диапазона int при вычитании "
                +((long)Integer.MIN_VALUE - 1));
        SimpleCalculator.diff(Integer.MIN_VALUE, 1);
    }
    @Test
    public void MultWithException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Переполнение диапазона int при умножении "
                +((long)Integer.MIN_VALUE * (-1)));
        SimpleCalculator.mult(Integer.MIN_VALUE, -1);
    }
    @Test
    public void DivWithException() {
        exception.expect(IntOverflowException.class);
        exception.expectMessage("Переполнение диапазона int при делении "
                +((long)Integer.MIN_VALUE / -1));
        SimpleCalculator.div(Integer.MIN_VALUE, -1);
    }
    @Test
    public void DivByZeroException() {
        exception.expect(ArithmeticException.class);
        exception.expectMessage("Деление на ноль! val2 = 0");
        SimpleCalculator.div(5, 0);
    }
}
