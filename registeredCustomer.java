import java.util.Scanner;
import java.util.Vector;
public class registeredCustomer{
    protected String Email;
    protected String Password;
    protected String Address;
    protected int Points;
    protected Vector<Order>ordersMade;
    protected Cart custCart;
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
    public void setPoints(int points) {
        Points = points;
    }
    private void Payment()
    {
        Order order = new Order();
        Database database = new Database();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the ID of the order you want to pay :");
        order.setID(in.nextInt()-1);
        System.out.println("Enter the status of the order you want to pay :");
        order.setStatus(in.next());
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
        custCart.clear();
        System.out.println("Done.");
    }
}
