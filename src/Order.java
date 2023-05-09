import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Order {
    private int Id;
    private String Status;
    private String DeliveryDate;
    private String PaymentMethod;
    private double TotalPrice;
    private Customer customer;
    private static final Map<Product,Integer> OProducts = new HashMap<>();
    public String GetDDate(){
        return DeliveryDate;
    }
    public double getTotalPrice() {
        return TotalPrice;
    }
    public Map<Product, Integer> getOProducts() {
        return OProducts;
    }
    public void setOProducts(Map<Product, Integer> cart){
        OProducts.clear();
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
    public void setStatus(String status) {
        Status = status;
    }
    public void setTotalPrice() {
        double totalPrice = 0;
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