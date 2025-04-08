public class LoanCalculator {
    public double calculateEMI(double principal, double rate, int term) {
        double monthlyRate = rate / 12 / 100;
        return (principal * monthlyRate * Math.pow(1 + monthlyRate, term)) / 
               (Math.pow(1 + monthlyRate, term) - 1);
    }
}