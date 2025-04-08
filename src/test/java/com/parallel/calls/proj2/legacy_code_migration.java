// Java 7 style code
public class TaxCalculator {
    public int calculateTotal(int[] salaries) {
        int total = 0;
        for (int i = 0; i < salaries.length; i++) {
            total += salaries[i];
        }
        return total;
    }
}