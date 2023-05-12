import java.util.HashMap;
import java.util.Map;

public class Order {
    private int Id;
    private String DeliveryDate;
    private String PaymentMethod;
    private double TotalPrice;
    private static final Map<Product,Integer> OProducts = new HashMap<>();
    public String getDDate(){
        return DeliveryDate;
    }
    public String getPMethod(){
        return PaymentMethod;
    }
    public double getTotalPrice() {
        return TotalPrice;
    }
    public Map<Product, Integer> getOProducts() {
        return OProducts;
    }
    public void setOProducts(Map<Product, Integer> cart){
        for (Map.Entry<Product, Integer> pair : cart.entrySet()) {
            Product key = pair.getKey();
            Integer value = pair.getValue();
            OProducts.put(key, value);
        }
    }
    public void setDDate(String deliveryDate) {
        DeliveryDate = deliveryDate;
    }
    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }
    public void setTotalPrice(double totalPrice) {
        totalPrice = 0.0;
        for (Map.Entry<Product, Integer> pair : OProducts.entrySet()) {
            Product key = pair.getKey();
            Integer value = pair.getValue();
            totalPrice += value*(key.getPrice() - (key.getDiscount()/100)*key.getPrice());
        }
        TotalPrice = totalPrice;
    }
    public void setID(int id) {
        Id = id;
    }
    public int getID() {
        return Id;
    }
}
