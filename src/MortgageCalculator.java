public class MortgageCalculator {
    private final static int MONTHS_PER_YEAR = 12;
    private final static int PERCENT = 100;

    private double principle;
    private double annualInterestRate;
    private int period;

    public MortgageCalculator(double principle, double annualInterestRate, int period) {
        this.principle = principle;
        this.annualInterestRate = annualInterestRate;
        this.period = period;
    }

    public double calculateMortgage() {
        double monthlyInterest = getMonthlyInterest();
        int months = getNumberOfMonths();
        return (principle * monthlyInterest
                * Math.pow((1 + monthlyInterest), months))
                / (Math.pow((1 + monthlyInterest), months) - 1);
    }

    public double calculateBalance(int paidPayments) {
        double monthlyInterest = getMonthlyInterest();
        int totInstallments = getNumberOfMonths();
        return principle
                * (Math.pow((1 + monthlyInterest), totInstallments) - Math.pow((1 + monthlyInterest), paidPayments))
                / (Math.pow((1 + monthlyInterest), totInstallments) - 1);
    }

    public double[] getRemainingBalances() {
        var balances = new double[getNumberOfMonths()];
        for (int month = 1; month <= balances.length; month++) {
            balances[month - 1] = calculateBalance(month);
        }
        return balances;
    }


    private int getNumberOfMonths() {
        return period * MONTHS_PER_YEAR;
    }

    private double getMonthlyInterest() {
        return annualInterestRate / (MONTHS_PER_YEAR * PERCENT);
    }
}