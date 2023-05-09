import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Catalog myCatalog = new Catalog();
        myCatalog.fillCatalog();
        MySystem ss = new MySystem();
        ss.EnterApplication();
        ss.ShowMenu();
    }
}