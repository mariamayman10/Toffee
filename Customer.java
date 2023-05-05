import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
public class Customer{
    protected String Email;
    protected String Password;
    protected String Address;
    protected int Points;
    protected Vector<Order>ordersMade = new Vector<>();
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
    public void Checkout()
    {
        Order order = new Order();
        Database database = new Database();
        Scanner in = new Scanner(System.in);
        order.setOProducts(Cart.getCart());
        order.setStatus("Not Delivered");
        System.out.println("Enter the delivery date of the order you want to pay :");
        order.setDDate(in.next());
        System.out.println("Enter the order date of the order you want to pay :");
        order.setDate(in.next());
        System.out.println("Enter the payment method of the order you want to pay :");
        order.setPaymentMethod(in.next());
        System.out.println("The total price of Your Order: " + order.getTotalPrice());
        database.SaveOrder(order);
        ordersMade.add(order);
        custCart.clearCart();
        System.out.println("Done.");
    }
}
