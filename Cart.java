import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cart {
    Scanner in = new Scanner(System.in);
    private static Map<Product,Integer> cart = new HashMap<Product,Integer>();
    public void addItem(Product product, Integer quantity) {
        cart.put(product,quantity);
    }
    public void clearCart() {
        cart.clear();
    }
    public void viewCart(){
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            System.out.println(entry.getKey().Name + " = " + entry.getValue());
        }
    }
}
