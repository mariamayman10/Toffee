import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Customer{
    private String Email;
    private String Password;
    private String Address;
    private int Points;
    private Vector<Order>ordersMade = new Vector<>();
    public Cart custCart = new Cart();

    /**
     *
     * @return
     */
    public String getEmail(){
        return Email;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return Password;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email){
        Email = email;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        Password = password;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address ) {
        Address = address;
    }

    /**
     *
     * @param other
     */
    /**
     *
     * @param other
     */
    public Customer(Customer other) {
        Email = other.Email;
        Password = other.Password;
        Address = other.Address;
        Points = other.Points;
        ordersMade = other.ordersMade;
        custCart = other.custCart;
    }
    public Customer(){}

    /**
     *
     * @return
     */
    public Order Checkout() {
        if(!custCart.getCart().isEmpty()) {
            Order order = new Order();
            Scanner in = new Scanner(System.in);
            order.setOProducts(this.custCart.getCart());
            order.setTotalPrice(0);
            System.out.println("Your Order Contains: ");
            this.custCart.viewCart();
            System.out.print("Enter the delivery day of the order (1-31):");
            String deliveryDay = in.next();
            while (Integer.parseInt(deliveryDay) < 1 || Integer.parseInt(deliveryDay) > 31) {
                System.out.print("\nInvalid day. Please enter a value between 1 and 31: ");
                deliveryDay = in.next();
            }
            System.out.print("Enter the delivery month of the order (1-12): ");
            String deliveryMonth = in.next();
            while (Integer.parseInt(deliveryMonth) < 1 || Integer.parseInt(deliveryMonth) > 12) {
                System.out.print("\nInvalid month. Please enter a value between 1 and 12: ");
                deliveryMonth = in.next();
            }
            String date= deliveryDay+"/"+deliveryMonth;
            order.setDDate(date);
            System.out.print("Enter the payment method for the order (Online or Cash):");
            String paymentMethod = in.next();
            while (!paymentMethod.equalsIgnoreCase("Online") && !paymentMethod.equalsIgnoreCase("Cash")) {
                System.out.print("Invalid payment method. Please enter either 'Online' or 'Cash': ");
                paymentMethod = in.next();
            }
            order.setPaymentMethod(paymentMethod);
            System.out.println("The Total Price of Your Order: " + order.getTotalPrice());
            ordersMade.add(order);
            this.custCart.clearCart(this);
            System.out.print("Done...");
            return order;
        }
        else{
            System.out.println("Your Cart is Empty");
            return null;
        }
    }
}
