import java.util.HashMap;
import java.util.Map;
public class Cart {
    private Map<Product,Integer>cart;
    public Cart(){
        cart= new HashMap<Product, Integer>();
    }
    public void addItem(Product product , int quantity) {
        Integer Quantites = cart.get(product);
        if(Quantites != null){
            quantity += Quantites;
        }else{
            cart.put(product,quantity);
        }
    }
    public void clearCart() {
        cart.clear();
    }
}
