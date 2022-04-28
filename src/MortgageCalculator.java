import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    final static int MONTHS_PER_YEAR = 12;
    final static int PERCENT = 100;

    public static void main(String[] args) {

        double principle = readInput("Principal ($1K - $1M): ",1000,1_000_000 );
        double annualInterestRate = readInput("Annual Interest Rate : ", 1, 30);
        int period = (int) readInput("Period (Years) : ", 1, 30);

        printMortgage(principle, annualInterestRate, period);

        paymentSchedule(principle, annualInterestRate, period);
    }

    private static void printMortgage(double principle, double annualInterestRate, int period) {
        double mortgage = calculateMortgage(principle, annualInterestRate, period);
        String formattedMortgage = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + formattedMortgage);
    }

    private static void paymentSchedule(double principle, double annualInterestRate, int period) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (int month = 1; month<= period *MONTHS_PER_YEAR; month++){
            double balance = calculateBalance(principle, annualInterestRate, period, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readInput(String sentence,double min, double max){
        double input;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print(sentence);
            input = scanner.nextDouble();
            if (input >= min && input <= max)
                break;
            System.out.println("Enter a number between " + min + " and " + max);
        }
        return input;
    }

    public static double calculateMortgage(
            double principle,
            double annualInterestRate,
            int period
    ) {
            double monthlyInterest = annualInterestRate / (MONTHS_PER_YEAR * PERCENT);
            int months = period * MONTHS_PER_YEAR;
            return  (principle * monthlyInterest
                    * Math.pow((1 + monthlyInterest), months))
                    / (Math.pow((1 + monthlyInterest), months) - 1);
    }
    public static double calculateBalance(
            double principle,
            double annualInterestRate,
            int period,
            int paidPayments
    ) {
        double monthlyInterest = annualInterestRate / (MONTHS_PER_YEAR * PERCENT);
        int totInstallments = period * MONTHS_PER_YEAR;
        return principle
                    * (Math.pow((1 + monthlyInterest), totInstallments) - Math.pow((1 + monthlyInterest), paidPayments))
                    / (Math.pow((1 + monthlyInterest), totInstallments) - 1);
    }
}
