import org.junit.Test;
import static org.junit.Assert.*;

/** Tests positive, negative, zero, invalid and equivalent inputs. */
public class RationalTest {
    private void assertFraction(long n, long d, Rational actual) {
        assertEquals(n, actual.numerator);
        assertEquals(d, actual.denominator);
    }

    @Test
    public void testDefaultConstructor() throws Rational.Illegal {
        assertFraction(0, 1, new Rational());
    }

    @Test
    public void testConstructorReducesFraction() throws Rational.Illegal {
        assertFraction(1, 2, new Rational(2, 4));
    }

    @Test
    public void testNegativeNumerator() throws Rational.Illegal {
        assertFraction(-1, 2, new Rational(-2, 4));
    }

    @Test
    public void testNegativeDenominator() throws Rational.Illegal {
        assertFraction(-1, 2, new Rational(2, -4));
    }

    @Test
    public void testBothNegative() throws Rational.Illegal {
        assertFraction(1, 2, new Rational(-2, -4));
    }

    @Test
    public void testZeroNumerator() throws Rational.Illegal {
        assertFraction(0, 1, new Rational(0, 5));
    }

    @Test(expected = Rational.Illegal.class)
    public void testZeroDenominator() throws Rational.Illegal {
        new Rational(1, 0);
    }

    @Test
    public void testAdd() throws Rational.Illegal {
        Rational x = new Rational(1, 2);
        x.add(new Rational(1, 4));
        assertFraction(3, 4, x);
    }

    @Test
    public void testAddNegative() throws Rational.Illegal {
        Rational x = new Rational(1, 2);
        x.add(new Rational(-3, 4));
        assertFraction(-1, 4, x);
    }

    @Test
    public void testAddZero() throws Rational.Illegal {
        Rational x = new Rational(1, 2);
        x.add(new Rational(0, 1));
        assertFraction(1, 2, x);
    }

    @Test
    public void testSubtract() throws Rational.Illegal {
        Rational x = new Rational(3, 4);
        x.subtract(new Rational(1, 2));
        assertFraction(1, 4, x);
    }

    @Test
    public void testSubtractToNegative() throws Rational.Illegal {
        Rational x = new Rational(1, 4);
        x.subtract(new Rational(1, 2));
        assertFraction(-1, 4, x);
    }

    @Test
    public void testSubtractToZero() throws Rational.Illegal {
        Rational x = new Rational(1, 2);
        x.subtract(new Rational(1, 2));
        assertFraction(0, 1, x);
    }

    @Test
    public void testMultiply() throws Rational.Illegal {
        Rational x = new Rational(2, 3);
        x.multiply(new Rational(3, 4));
        assertFraction(1, 2, x);
    }

    @Test
    public void testMultiplyNegative() throws Rational.Illegal {
        Rational x = new Rational(-2, 3);
        x.multiply(new Rational(3, 4));
        assertFraction(-1, 2, x);
    }

    @Test
    public void testMultiplyZero() throws Rational.Illegal {
        Rational x = new Rational(2, 3);
        x.multiply(new Rational(0, 1));
        assertFraction(0, 1, x);
    }

    @Test
    public void testDivide() throws Rational.Illegal {
        Rational x = new Rational(1, 2);
        x.divide(new Rational(3, 4));
        assertFraction(2, 3, x);
    }

    @Test
    public void testDivideNegative() throws Rational.Illegal {
        Rational x = new Rational(1, 2);
        x.divide(new Rational(-3, 4));
        assertFraction(-2, 3, x);
    }

    @Test
    public void testZeroDividedByNonzero() throws Rational.Illegal {
        Rational x = new Rational(0, 1);
        x.divide(new Rational(3, 4));
        assertFraction(0, 1, x);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() throws Rational.Illegal {
        new Rational(1, 2).divide(new Rational(0, 1));
    }

    @Test
    public void testEqualsEquivalent() throws Rational.Illegal {
        assertTrue(new Rational(1, 2).equals(new Rational(2, 4)));
    }

    @Test
    public void testEqualsDifferent() throws Rational.Illegal {
        assertFalse(new Rational(1, 2).equals(new Rational(1, 3)));
    }

    @Test
    public void testEqualsNull() throws Rational.Illegal {
        assertFalse(new Rational(1, 2).equals(null));
    }

    @Test
    public void testEqualsOtherType() throws Rational.Illegal {
        assertFalse(new Rational(1, 2).equals("1/2"));
    }

    @Test
    public void testCompareLess() throws Rational.Illegal {
        assertEquals(-1L, new Rational(1, 3).compareTo(new Rational(1, 2)));
    }

    @Test
    public void testCompareEqual() throws Rational.Illegal {
        assertEquals(0L, new Rational(1, 2).compareTo(new Rational(2, 4)));
    }

    @Test
    public void testCompareGreater() throws Rational.Illegal {
        assertEquals(1L, new Rational(3, 4).compareTo(new Rational(1, 2)));
    }

    @Test
    public void testCompareNegative() throws Rational.Illegal {
        assertEquals(-1L, new Rational(-1, 2).compareTo(new Rational(0, 1)));
    }

    @Test
    public void testToString() throws Rational.Illegal {
        assertEquals("1/2", new Rational(2, 4).toString());
    }

    @Test
    public void testNegativeToString() throws Rational.Illegal {
        assertEquals("-1/2", new Rational(-1, 2).toString());
    }

    @Test
    public void testZeroToString() throws Rational.Illegal {
        assertEquals("0/1", new Rational(0, 5).toString());
    }

    @Test
    public void testDivideBySelf() throws Rational.Illegal {
        Rational x = new Rational(2, 3);
        x.divide(x);
        assertFraction(1, 1, x);
    }

    @Test
    public void testEquivalentHashCodes() throws Rational.Illegal {
        assertEquals(new Rational(1, 2).hashCode(), new Rational(2, 4).hashCode());
    }

    @Test
    public void testCompareLargeValues() throws Rational.Illegal {
        assertEquals(1L, new Rational(Long.MAX_VALUE, 2)
            .compareTo(new Rational(Long.MAX_VALUE, 3)));
    }
    @Test
    public void testLargeAdditionWithoutIntermediateOverflow() throws Rational.Illegal {
        Rational x = new Rational(Long.MAX_VALUE, 2);
        x.add(new Rational(Long.MAX_VALUE, 2));
        assertFraction(Long.MAX_VALUE, 1, x);
    }

    @Test
    public void testMinimumLongSigns() throws Rational.Illegal {
        assertFraction(1, 1, new Rational(Long.MIN_VALUE, Long.MIN_VALUE));
    }

    @Test(expected = ArithmeticException.class)
    public void testUnrepresentableResult() throws Rational.Illegal {
        Rational x = new Rational(Long.MAX_VALUE, 1);
        x.add(new Rational(1, 1));
    }

    @Test
    public void testOverflowLeavesOriginalValue() throws Rational.Illegal {
        Rational x = new Rational(Long.MAX_VALUE, 1);
        try {
            x.add(new Rational(1, 1));
            fail("Expected overflow");
        } catch (ArithmeticException expected) {
            assertFraction(Long.MAX_VALUE, 1, x);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testCompareNull() throws Rational.Illegal {
        new Rational(1, 2).compareTo(null);
    }

    @Test(expected = ClassCastException.class)
    public void testCompareWrongType() throws Rational.Illegal {
        new Rational(1, 2).compareTo("1/2");
    }
}
