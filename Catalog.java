import java.util.Vector;

public class Catalog {
    public static Vector<Product> catalog = new Vector<Product>();

    public void ShowCatalog(){
        for (int i = 0; i < catalog.size(); i++){
            Product P = catalog.get(i);
            System.out.print("Product Name: ");
            P.getName();
            System.out.print("\nProduct Category: ");
            P.getCategory();
            if(P.getDiscount() != 0){
                double NewPrice = P.getPrice() - ((P.getDiscount() / 100) * P.getPrice());
                System.out.print("\nThere is a Discount! Product Price After Discount: ");
                P.getPrice();
            }
            System.out.print("\nProduct Price: ");
            P.getPrice();
        }
    }
}
