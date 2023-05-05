import java.util.HashMap;
import java.util.Map;

public class Cart {
    private static final Map<Product,Integer> cart = new HashMap<>();
    public void addItem(Product product, Integer quantity) {
        for (Map.Entry<Product, Integer> pair : cart.entrySet()) {
            if(product.getID() == pair.getKey().getID()){
                pair.setValue(pair.getValue() + quantity);
                return;
            }
        }
        cart.put(product,quantity);
    }
    public void clearCart() {
        cart.clear();
    }
    public void viewCart(){
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            System.out.println(entry.getKey().getName() + " = " + entry.getValue());
        }
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }
}
