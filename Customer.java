import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
public class Customer{
    private String Email;
    private String Password;
    private String Address;
    private int Points;
    private Vector<Order>ordersMade = new Vector<>();
    public Cart custCart = new Cart();
    public String getEmail(){
        return Email;
    }
    public String getPassword() {
        return Password;
    }
    public void setEmail(String email){
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }
    public void setAddress(String address ) {
        Address = address;
    }
    public Customer(Customer other) {
        Email = other.Email;
        Password = other.Password;
        Address = other.Address;
        Points = other.Points;
        ordersMade = other.ordersMade;
        custCart = other.custCart;
    }
    public Customer(){}
    public Order Checkout()
    {
        Order order = new Order();
        Scanner in = new Scanner(System.in);
        order.setOProducts(custCart.getCart());
        order.setTotalPrice(0);
        System.out.println("Your Order Contains: ");
        custCart.viewCart();
        order.setStatus("Not Delivered");
        System.out.println("Enter the delivery date of the order:");
        order.setDDate(in.next());
        System.out.println("Enter the payment method of the order you want to pay:");
        order.setPaymentMethod(in.next());
        System.out.println("The total price of Your Order: " + order.getTotalPrice());
        ordersMade.add(order);
        custCart.clearCart();
        System.out.println("Done.");
        return order;
    }
}
