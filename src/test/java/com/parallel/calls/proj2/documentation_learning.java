public class CSVParser {
    public void parse(String csv) {
        String[] lines = csv.split("\n");
        for (String line : lines) {
            String[] values = line.split(",");
            System.out.println(values[0]);
        }
    }
}