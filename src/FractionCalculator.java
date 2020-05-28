import java.util.Scanner;

public class FractionCalculator {

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.println("This program is a fraction calculator");
        System.out.println("It will add, subtract, multiply, and divide fractions until you type Q to quit");
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers.");
        boolean run = true;

        while(run){
            String operator = getOperation(scanner);
            Fraction fraction1 = getFraction(scanner);
            Fraction fraction2 = getFraction(scanner);
            Fraction fraction3;
            switch (operator.charAt(0)){
                case '+':
                    fraction3 = fraction1.add(fraction2);
                    fraction3.toLowestTerms();
                    print(fraction1, fraction2, fraction3, "+");
                    break;
                case '-':
                    fraction3 = fraction1.subtract(fraction2);
                    fraction3.toLowestTerms();
                    print(fraction1, fraction2, fraction3, "-");
                    break;
                case '*':
                    fraction3 = fraction1.multiply(fraction2);
                    fraction3.toLowestTerms();
                    print(fraction1, fraction2, fraction3, "*");
                    break;
                case '/':
                    fraction3 = fraction1.divide(fraction2);
                    fraction3.toLowestTerms();
                    print(fraction1, fraction2, fraction3, "/");
                    break;
                case '=':
                    print(fraction1, fraction2, fraction1.equals(fraction2), "=");
            }
        }

    }

    private static String getOperation(Scanner scanner){
        System.out.print("Please enter a valid mathematical operation (+, -, /, *, = or Q to quit): ");
        String response = scanner.next();
        switch (response.charAt(0)) {
            case '+':
            case '-':
            case '/':
            case '*':
            case '=':
            case 'q':
                return response;
            case 'Q':
                System.exit(1);
            default:
                System.out.print("Invalid Operation. ");
                return getOperation(scanner);
        }
    }

    private static boolean validFraction(String input){
        if(input.matches(".*[a-z]+.*")) return false;
//        int index = input.indexOf('/');
//        int a = Integer.parseInt(input.substring(0,index - 1));
//        int b = Integer.parseInt(input.substring(index + 1, input.length()));
        if (input.contains("/")) {
            String array[] = input.split("/");
            int a = Integer.parseInt(array[0]);
            int b = Integer.parseInt(array[1]);
            if (a > 0 && b > 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    private static Fraction getFraction(Scanner scanner){
        System.out.print("Please enter a fraction (a/b) or integer (a): ");
        String input = scanner.next();
        System.out.println("");
        if(!validFraction(input)){
            System.out.print("Invalid Fraction: ");
            return getFraction(scanner);
        }

        if(input == null){
            return new Fraction();
        }

        if(input.contains("/")) {
            String array[] = input.split("/", 2);
            int a = Integer.parseInt(array[0]);
            int b = Integer.parseInt(array[1]);
            return new Fraction(a, b);
        }

        return new Fraction(Integer.parseInt(input));
    }

    private static void print(Fraction fraction1, Fraction fraction2, Fraction result, String operator){
        System.out.println(fraction1.toString() + " " + operator + " " + fraction2.toString() + " = " + result);
        System.out.println("");
    }

    private static void print(Fraction fraction1, Fraction fraction2, boolean result, String operator){
        System.out.println(fraction1.toString() + " " + operator + " " + fraction2.toString() + " = " + String.valueOf(result));
        System.out.println("");
    }
}
