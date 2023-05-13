import java.util.Vector;
/**
 * The Catalog class represents a catalog of products . The class contains a Vector of Product objects representing the products in the catalog.The class provides a method fillCatalog() that adds several Product objects to the catalog Vector.
 */
public class Catalog {
    /**
     * used to store the catalog items in it
     */
    public static Vector<Product> catalog = new Vector<>();

    /**
     * fills the catalog items in the catalog
     */
    public void fillCatalog(){
        Product P1 = new Product(10.0,"Dark chocolate","chocolate",1.0,1,20);
        Catalog.catalog.add(P1);
        Product P2 = new Product(20.0,"White chocolate","chocolate",2.0,2,20);
        Catalog.catalog.add(P2);
        Product P3 = new Product(5.0,"Lollipop","Sugar candy",0.0,3,20);
        Catalog.catalog.add(P3);
        Product P4 = new Product(30.0,"Caramel Bonbons","bonbon",0.6,4,30);
        Catalog.catalog.add(P4);
        Product P5 = new Product(35.0,"chocolate Bonbons","bonbon",0.5,5,20);
        Catalog.catalog.add(P5);
        Product P6 = new Product(60.0,"Toffee","Candy",5,6,20);
        Catalog.catalog.add(P6);
    }

}