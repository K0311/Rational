import java.math.BigInteger;

/** A mutable fraction, stored in reduced form with a positive denominator. */
class Rational {
    long numerator;
    long denominator;

    class Illegal extends Exception {
        private static final long serialVersionUID = 1L;
        final String reason;

        Illegal(String reason) {
            super(reason);
            this.reason = reason;
        }
    }

    Rational() {
        numerator = 0;
        denominator = 1;
    }

    Rational(long numerator, long denominator) throws Illegal {
        if (denominator == 0) {
            throw new Illegal("Denominator cannot be zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplestForm();
    }

    private void simplestForm() {
        setReduced(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    /** Reduce before converting to long; reject unrepresentable results explicitly. */
    private void setReduced(BigInteger n, BigInteger d) {
        if (d.signum() == 0) {
            throw new ArithmeticException("Denominator cannot be zero");
        }
        if (d.signum() < 0) {
            n = n.negate();
            d = d.negate();
        }
        BigInteger gcd = n.gcd(d);
        long reducedNumerator = n.divide(gcd).longValueExact();
        long reducedDenominator = d.divide(gcd).longValueExact();
        numerator = reducedNumerator;
        denominator = reducedDenominator;
    }

    public void add(Rational x) {
        BigInteger a = BigInteger.valueOf(numerator);
        BigInteger b = BigInteger.valueOf(denominator);
        BigInteger c = BigInteger.valueOf(x.numerator);
        BigInteger d = BigInteger.valueOf(x.denominator);
        setReduced(a.multiply(d).add(c.multiply(b)), b.multiply(d));
    }

    public void subtract(Rational x) {
        BigInteger a = BigInteger.valueOf(numerator);
        BigInteger b = BigInteger.valueOf(denominator);
        BigInteger c = BigInteger.valueOf(x.numerator);
        BigInteger d = BigInteger.valueOf(x.denominator);
        setReduced(a.multiply(d).subtract(c.multiply(b)), b.multiply(d));
    }

    public void multiply(Rational x) {
        setReduced(BigInteger.valueOf(numerator).multiply(BigInteger.valueOf(x.numerator)),
            BigInteger.valueOf(denominator).multiply(BigInteger.valueOf(x.denominator)));
    }

    public void divide(Rational x) {
        if (x.numerator == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        setReduced(BigInteger.valueOf(numerator).multiply(BigInteger.valueOf(x.denominator)),
            BigInteger.valueOf(denominator).multiply(BigInteger.valueOf(x.numerator)));
    }

    @Override
    public boolean equals(Object x) {
        if (this == x) {
            return true;
        }
        if (!(x instanceof Rational)) {
            return false;
        }
        Rational other = (Rational) x;
        return numerator == other.numerator && denominator == other.denominator;
    }

    @Override
    public int hashCode() {
        return 31 * Long.hashCode(numerator) + Long.hashCode(denominator);
    }

    public long compareTo(Object x) {
        if (x == null) {
            throw new NullPointerException("Cannot compare to null");
        }
        if (!(x instanceof Rational)) {
            throw new ClassCastException("Expected a Rational number");
        }
        Rational other = (Rational) x;
        BigInteger left = BigInteger.valueOf(numerator)
            .multiply(BigInteger.valueOf(other.denominator));
        BigInteger right = BigInteger.valueOf(other.numerator)
            .multiply(BigInteger.valueOf(denominator));
        return left.compareTo(right);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public static void main(String[] args) {
        System.out.println("This is Rational class.");
    }
}
