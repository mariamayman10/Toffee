import java.io.IOException;

public class Main {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Database Db=new Database();
        Catalog myCatalog = new Catalog();
        myCatalog.fillCatalog();
        MySystem ss = new MySystem();
        ss.enterApplication();
        ss.showMenu();
    }
}
