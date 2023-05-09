import java.io. * ;
import java.util.Scanner;
public class CSVReaderDemo {
    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("Test.csv"));
        //parsing a CSV file into the constructor of Scanner class
        sc.useDelimiter(",");
        //setting comma as delimiter pattern

        while (sc.hasNext()) {
            String Email , Pass;
            Email = sc.next();
            Pass = sc.next();
        }
        sc.close();
        //closes the scanner
    }
}