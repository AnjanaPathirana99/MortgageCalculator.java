public class Main {

    public static void main(String[] args) {

        double principle = Console.readInput("Principal ($1K - $1M): ",1000,1_000_000 );
        double annualInterestRate = Console.readInput("Annual Interest Rate : ", 1, 30);
        int period = (int) Console.readInput("Period (Years) : ", 1, 30);
        var calculator = new MortgageCalculator(principle, annualInterestRate, period);
        new MortgageReport(calculator).printMortgage();
        new MortgageReport(calculator).paymentSchedule();
    }
}
