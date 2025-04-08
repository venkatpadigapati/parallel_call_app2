public class TaxService {
    public double calculateTax(double income) {
        if (income <= 250000) return 0;
        else if (income <= 500000) return income * 0.05;
        else return income * 0.1;
    }
}