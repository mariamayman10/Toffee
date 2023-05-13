import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * The Customer class contains instance variables for the customer's email, password, address and a Vector of orders made by the customer.The class also contains a Cart object representing the customer's shopping cart.The class provides methods to get and set the customer's email,password, and address, as well as a Checkout method that allows the customer to place an order by entering delivery and payment information, receiving an OTP by email, and confirming the OTP to complete the order.
 *
 */
public class Customer{
    /**
     * String that stores the email.
     */
    private String Email;
    /**
     * String that stores the password
     */
    private String Password;
    /**
     * String that stores the Address
     */
    private String Address;
    /**
     * variable is an integer that stores the number of points accumulated by the customer
     */
    private int Points;
    /**
     * variable is a Vector object that stores a list of orders made by the customer.
     */
    private Vector<Order>ordersMade = new Vector<>();
    /**
     * object that represents the customer shopping cart
     */
    public Cart custCart = new Cart();

    /**
     * method that returns the value of a variable Email as a String
     * @return
     */
    public String getEmail(){
        return Email;
    }

    /**
     *method that returns the value of a variable Password as a String
     * @return
     */
    public String getPassword() {
        return Password;
    }

    /**
     *method that sets the value of an instance variable Email to the value passed in as a parameter.
     * @param email
     */
    public void setEmail(String email){
        Email = email;
    }

    /**
     *method that sets the value of an instance variable password to the value passed in as a parameter
     * @param password
     */
    public void setPassword(String password) {
        Password = password;
    }

    /**
     *method that sets the value of an instance variable Address to the value passed in as a parameter
     * @param address
     */
    public void setAddress(String address ) {
        Address = address;
    }

    /**
     *The constructor initializes the new Customer object's instance variables with the values of the corresponding instance variables of the "other" "Customer" object that is passed in as a parameter
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
     *This method is used to place an order by a customer the method takes the customer cart, prompts the user to enter delivery and payment information, calculates the total price of the order, generates an OTP code, adds the order to the list of orders made, and clears the customer cart.
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
            System.out.print("Receive order: 1-On the same Address 2-Enter new address: ");
            String Choice = in.next();
            while(Choice != "1" && Choice != "2"){
                System.out.print("\nInvalid Choice..Please Enter 1 or 2");
                Choice = in.next();
            }
            if(Choice == "2"){
                System.out.println("Please Enter the New Address");
                String Address = in.next();
                order.setAddress(Address);
            }
            else order.setAddress(this.Address);
            System.out.println("The Total Price of Your Order: " + order.getTotalPrice());
            Random random = new Random();
            int otpCode = random.nextInt(100,999);
            String S_otpCode = Integer.toString(otpCode);
            OTP sendotp = new OTP();
            sendotp.sendOTPEmail(this.Email, S_otpCode);
            System.out.print("Enter the OTP code you received: ");
            String entered_OTP = in.next();
            if(entered_OTP.equals(S_otpCode)){
                ordersMade.add(order);
                this.custCart.clearCart(this);
                System.out.print("Done...");
                System.out.println("\nOrder Placed Successfully");
            }
            else{
                System.out.println("\nInvalid OTP..Can't Place the order");
            }

            return order;
        }
        else{
            System.out.println("Your Cart is Empty");
            return null;
        }
    }
}