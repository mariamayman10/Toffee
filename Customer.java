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
    public String getAddress() {
        return Address;
    }
    public int getPoints() {
        return Points;
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
    public Customer(){
    }
    public void Checkout()
    {
        Order order = new Order();
        Database database = new Database();
        Scanner in = new Scanner(System.in);
        order.setStatus("Not Delivered");
        System.out.println("Enter the delivery date of the order you want to pay :");
        order.setDDate(in.next());
        System.out.println("Enter the order date of the order you want to pay :");
        order.setDate(in.next());
        System.out.println("Enter the payment method of the order you want to pay :");
        order.setPaymentMethod(in.next());
        System.out.println("Enter the total price of the order you want to pay :");
        order.setTotalPrice(in.nextDouble());
        database.SaveOrder(order);
        ordersMade.add(order);
        custCart.clearCart();
        System.out.println("Done.");
    }
}
//total price in check out
//update available quantity
