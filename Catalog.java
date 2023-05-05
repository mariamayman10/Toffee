import java.util.Vector;

public class Catalog {
    public static Vector<Product> catalog = new Vector<>();
    public void fillCatalog(){
        Product P1 = new Product(10.0,"Dark chocolate","chocolate",1.0,0,20);
        Catalog.catalog.add(P1);
        Product P2 = new Product(20.0,"White chocolate","chocolate",2.0,1,20);
        Catalog.catalog.add(P2);
        Product P3 = new Product(5.0,"Lolipop","Sugar candy",0.0,2,20);
        Catalog.catalog.add(P3);
        Product P4 = new Product(30.0,"Caramel Bonbons","bonbon",0.6,3,30);
        Catalog.catalog.add(P4);
        Product P5 = new Product(35.0,"chocolate Bonbons","bonbon",0.5,4,20);
        Catalog.catalog.add(P5);
    }
}
