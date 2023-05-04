public class Main {
    public static void main(String[] args) {
       Catalog atalog = new Catalog();
       for(int i = 0;i<5;i++){
           Product pro = new Product(10.0+i,"Aya"+i,"C"+i,1.0+i,0+i,0+i);
           atalog.catalog.add(pro);
       }
       MySystem ss = new MySystem();
       ss.EnterApplication();
       ss.ShowMenu();

       //ss.ShowCatalog();
    }
}
