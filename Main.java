import java.io.IOException;
/**
 * The Main class is the main class of program The class creates a Database object, a Catalog object, and a MySystem object, and calls the fillCatalog() method of the Catalog object to fill the catalog with predefined Product objects. The class then calls the enterApplication() and showMenu() methods of the MySystem object to start the application and display the main menu to the user. The main method also throws an IOException in case any input/output errors occur during the execution of the program.
 */
public class Main {

    /**
     * here we will start our program
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Catalog myCatalog = new Catalog();
        myCatalog.fillCatalog();
        MySystem ss = new MySystem();
        ss.enterApplication();
        ss.showMenu();
    }
}