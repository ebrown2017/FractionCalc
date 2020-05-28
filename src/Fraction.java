import java.util.stream.IntStream;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator){
        if(denominator == 0){
            throw new IllegalArgumentException("Denominator cannot be 0.");
        } else if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(int numerator){
        this.numerator = numerator;
        this.denominator = 1;
    }

    public Fraction(){
        this.numerator = 0;
        this.denominator = 1;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator(){
        return denominator;
    }

    @Override
    public String toString(){
        return numerator + "/" + denominator;
    }

    public double toDouble(){
        return numerator/denominator;
    }

    public Fraction add(Fraction fraction){
        int num = numerator * fraction.getDenominator() + denominator * fraction.getNumerator();
        int deno = denominator * fraction.getDenominator();
        return new Fraction(num, deno);
    }

    public Fraction subtract(Fraction fraction){
        if (fraction.getDenominator() == denominator){
           return new Fraction(numerator - fraction.getNumerator(), denominator);
        }
        int lcm = lcm(denominator, fraction.getDenominator());

        return new Fraction((numerator * (lcm/numerator)) - (fraction.getNumerator() * (lcm/fraction.getNumerator())), lcm);
    }

    public static int lcm(int num1, int num2){
        int bigger = Math.max(num1, num2);
        int smaller = Math.max(num1, num2);

        return IntStream.rangeClosed(1,smaller)
                        .filter(factor -> (factor * bigger) % smaller == 0)
                        .map(factor -> Math.abs(factor * bigger))
                        .findFirst()
                        .getAsInt();
    }

    public Fraction multiply(Fraction fraction){
        return new Fraction(numerator * fraction.getNumerator(), denominator * fraction.getDenominator());
    }

    public Fraction divide(Fraction fraction){
        if (fraction.getDenominator() == 0){
            throw new IllegalArgumentException("Cannot divide by 0.");
        }
        return new Fraction(numerator * fraction.getDenominator(), denominator * fraction.getNumerator());
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof Fraction)){
            return false;
        }

        if(((Fraction) other).getNumerator() == numerator && ((Fraction) other).getDenominator() == denominator){
            return true;
        }

        return false;
    }

    public void toLowestTerms(){
        int a = numerator;
        int b = denominator;

        int temp = gcd(a,b);
        denominator /= temp;
        numerator /= temp;
    }

    public static int gcd(int a, int b){
        int temp;

        while (a != 0 && b != 0){
            temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}
