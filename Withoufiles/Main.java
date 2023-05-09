public class Main {
    public static void main(String[] args) {
       Catalog myCatalog = new Catalog();
       myCatalog.fillCatalog();
       MySystem ss = new MySystem();
       ss.EnterApplication();
       ss.ShowMenu();
    }
}
