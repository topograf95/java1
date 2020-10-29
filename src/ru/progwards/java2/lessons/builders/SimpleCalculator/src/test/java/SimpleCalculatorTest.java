import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class SimpleCalculatorTest {

    @RunWith(Parameterized.class)
    public static class SumCalculatorTest {
        public int val1;
        public int val2;
        public int expected;

        public SumCalculatorTest(int val1, int val2, int expected) {
            this.val1 = val1;
            this.val2 = val2;
            this.expected = expected;
        }

        @Parameterized.Parameters(name = "Тест {index}: {0} + {1} = {2}")
        public static Iterable<Integer[]> dataForTestSum() {
            return Arrays.asList(new Integer[][]{
                    {0, 0, 0},
                    {5, 0, 5},
                    {-5, -5, -10},
                    {34, 55, 89},
                    {-34, -55, -89}
            });
        }
        @Test
        public void testSumWithParams() {
            assertEquals(expected, SimpleCalculator.sum(val1, val2));
        }
    }

    @RunWith(Parameterized.class)
    public static class DiffCalculatorTest {
        public int val1;
        public int val2;
        public int expected;

        public DiffCalculatorTest(int val1, int val2, int expected) {
            this.val1 = val1;
            this.val2 = val2;
            this.expected = expected;
        }

        @Parameterized.Parameters(name = "Тест {index}: {0} - {1} = {2}")
        public static Iterable<Integer[]> dataForTestDiff() {
            return Arrays.asList(new Integer[][]{
                    {0, 0, 0},
                    {0, 5, -5},
                    {-5, -5, 0},
                    {55, 30, 25},
                    {-34, 55, -89}
            });
        }
        @Test
        public void testDiffWithParams() {
            assertEquals(expected, SimpleCalculator.diff(val1, val2));
        }
    }

    @RunWith(Parameterized.class)
    public static class MultCalculatorTest {
        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int expected;

        @Parameterized.Parameters(name = "Тест {index}: {0} * {1} = {2}")
        public static Iterable<Integer[]> dataForTestMult() {
            return Arrays.asList(new Integer[][]{
                    {1, 0, 0},
                    {-1, 5, -5},
                    {-5, -5, 25},
            });
        }
        @Test
        public void testMultWithParams() {
            assertEquals(expected, SimpleCalculator.mult(val1, val2));
        }
    }

    @RunWith(Parameterized.class)
    public static class DivCalculatorTest {
        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int expected;

        @Parameterized.Parameters(name = "Тест {index}: {0} / {1} = {2}")
        public static Iterable<Integer[]> dataForTestDiv() {
            return Arrays.asList(new Integer[][]{
                    {0, 1, 0},
                    {-5, 2, -2},
                    {-25, -5, 5},
            });
        }
        @Test
        public void testDivWithParams() {
            assertEquals(expected, SimpleCalculator.div(val1, val2));
        }
    }
}
