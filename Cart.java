import java.util.HashMap;
import java.util.Map;

public class Cart {
    private static Map<Product,Integer> cart = new HashMap<>();
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
