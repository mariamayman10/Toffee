import java.util.HashMap;
import java.util.Map;

public class Order {
    private int Id;
    private String Status;
    private String deliveryDate;
    private String paymentMethod;
    private double totalPrice;
    private Customer customer;
    private static final Map<Product,Integer> OProducts = new HashMap<>();
    public String getDDate(){
        return deliveryDate;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public Map<Product, Integer> getOProducts() {
        return OProducts;
    }

    /**
     *
     * @param cart
     */
    public void setOProducts(Map<Product, Integer> cart){
        OProducts.clear();
        for (Map.Entry<Product, Integer> pair : cart.entrySet()) {
            Product key = pair.getKey();
            Integer value = pair.getValue();
            OProducts.put(key, value);
        }
    }

    /**
     *
     * @param deliveryDate
     */
    public void setDDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     *
     * @param paymentMethod
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     *
     * @param status
     */
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
        this.totalPrice = totalPrice;
    }

    /**
     *
     * @param id
     */
    public void setID(int id) {
        Id = id;
    }
    public int getID() {
        return Id;
    }
}