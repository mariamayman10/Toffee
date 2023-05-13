import java.util.HashMap;
import java.util.Map;
/**
 * The Order class  The class contains instance variables for the order's ID, delivery date,payment method, and total price.The class also contains a Map of ordered products, where the keys are Product objects and the values are integers representing the quantity of each product in the order. The class provides methods to get and set the delivery date, payment method, total price, and ordered products. The Order class also has methods to calculate the total price of the order based on the products and their quantities,and to get and set the order ID.
 */
public class Order {
    /**
     * ID of the order
     */
    private int Id;
    /**
     * delivery date of the order
     */
    private String DeliveryDate;
    /**
     * PaymentMethod of the order
     */
    private String PaymentMethod;
    /**
     * TotalPrice of the order
     */
    private double TotalPrice;
    /**
     * Address that will get the order
     */
    private String Address;
    /**
     * the products contained in the order with their quantity
     */

    private static final Map<Product,Integer> OProducts = new HashMap<>();

    public Order() {
    }

    /**
     * to get the delivery date of the order
     * @return
     */
    public String getDDate(){
        return DeliveryDate;
    }
    /**
     * to get the payment method of the order
     * @return
     */
    public String getPMethod(){
        return PaymentMethod;
    }
    /**
     * to get the total price of the order
     * @return
     */
    public double getTotalPrice() {
        return TotalPrice;
    }
    /**
     * to put the products with their quantity in the map
     * @return
     */
    public void setOProducts(Map<Product, Integer> cart){
        for (Map.Entry<Product, Integer> pair : cart.entrySet()) {
            Product key = pair.getKey();
            Integer value = pair.getValue();
            OProducts.put(key, value);
        }
    }
    /**
     * to set the delivery date of the order
     */
    public void setDDate(String deliveryDate) {
        DeliveryDate = deliveryDate;
    }
    /**
     * to set the payment method of the order
     */
    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }
    /**
     * to set the total price of the order after adding the discount
     */
    public void setTotalPrice(double totalPrice) {
        totalPrice = 0.0;
        for (Map.Entry<Product, Integer> pair : OProducts.entrySet()) {
            Product key = pair.getKey();
            Integer value = pair.getValue();
            totalPrice += value*(key.getPrice() - (key.getDiscount()/100)*key.getPrice());
        }
        TotalPrice = totalPrice;
    }
    /**
     * to set the ID of the order
     */
    public void setID(int id) {
        Id = id;
    }
    /**
     * to get the ID of the order
     * @return
     */
    public int getID() {
        return Id;
    }
    /**
     * Set the address of the order
     */
    public void setAddress(String add){
        Address = add;
    }
}