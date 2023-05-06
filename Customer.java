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
        if(!custCart.getCart().isEmpty()){
            Order order = new Order();
            Scanner in = new Scanner(System.in);
            order.setOProducts(custCart.getCart());
            order.setTotalPrice(0);
            System.out.println("Your Order Contains: ");
            custCart.viewCart();
            order.setStatus("Not Delivered");
            System.out.println("Enter the delivery date of the order:");
            String DDate = in.next();
            String regex = "\\b(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)\\b";
            boolean ValidD = DDate.matches(regex);
            while(!ValidD){
                DDate = in.next();
                ValidD = DDate.matches(regex);
            }
            order.setDDate(in.next());
            while(true){
                System.out.println("Enter the Payment Method of the order you want to pay:");
                if(in.next().equalsIgnoreCase("cash")){
                    order.setPaymentMethod(in.next());
                    break;
                }
                else System.out.println("Please Choose Either Cash or Online..Enter the payment method of the order you want to pay:");
            }
            System.out.println("The Total Price of Your Order: " + order.getTotalPrice());
            ordersMade.add(order);
            custCart.clearCart();
            System.out.println("Done.");
            return order;
        }
        else{
            System.out.println("Your Cart is Empty");
            return null;
        }
    }
}
