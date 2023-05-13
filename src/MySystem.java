import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
/**
 * The MySystem class allows users to view products, add items to their cart, place orders, and create accounts. The class interacts with a Database class to perform operations on the program's data,and contains methods for displaying menus, logging in, signing up, and handling user input.
 */
public class MySystem {
    Scanner in = new Scanner(System.in);
    Database DB = new Database();

    /**
     * this function will remove the delivered orders from the system when they are delivered
     * @throws IOException
     */
    public void enterApplication() throws IOException {
        LocalDate currentDate = LocalDate.now();
        String Day = String.valueOf(currentDate.getDayOfMonth());
        String Month = String.valueOf(currentDate.getMonthValue());
        DB.removeOrder(Day + '/' + Month);
        System.out.println("_________Welcome to Toffee_________");
    }

    /**
     * this function will show the menu for the people who are logged in to the system
     * @param customer
     * @throws IOException
     */
    public void loggedIn(Customer customer) throws IOException {
        while(true) {
            System.out.println("Want: 1)View Products 2)Add Item To Cart 3)Place Order 4)Log Out");
            int choice = in.nextInt();
            while (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
                System.out.print("Please Choose Either 1, 2 , 3 or 4: ");
                choice = in.nextInt();
            }
            if (choice == 1) showCatalog();
            else if (choice == 2) {
                System.out.print("Please Enter the number of the product: ");
                int Pid = in.nextInt();
                System.out.print("Please Enter the quantity of the product: ");
                int Quantity = in.nextInt();
                if(DB.searchForProduct(Pid) == null) System.out.println("There is no Such a Product");
                else {
                    Product Product = new Product(DB.searchForProduct(Pid));
                    if (DB.updateCatalog(Product, Quantity)) customer.custCart.addItem(Product, Quantity);
                }
            }
            else if (choice == 3)DB.saveOrder(customer.Checkout());
            else {
                customer.custCart.saveCart(customer);
                break;
            }
        }
    }

    /**
     *this function will show the menu for the people who are not logged in to the system
     * @throws IOException
     */
    public void showMenu() throws IOException{
        while(true){
            System.out.println("Go to: 1)SignUp 2)Login 3)View Products 4)Exit");
            int choice = in.nextInt();
            while (choice != 1 && choice != 2 && choice != 3 && choice != 4){
                System.out.println("Please Choose Either 1, 2 , 3 or 4");
                choice = in.nextInt();
            }
            if(choice == 1) showSignUp();
            else if(choice == 2) showLogIn();
            else if(choice == 3) {
                showCatalog();
                showMenu();
            }
            else System.exit(0);
        }
    }
    /**
     *this function will show the menu for the people who want to sign up
     * @throws IOException
     */
    public void showSignUp() throws IOException{
        System.out.println("____SIGN UP____");
        in.nextLine();
        System.out.print("Enter Your Name: ");
        String Name = in.nextLine();
        System.out.print("\nEnter Your Email: ");
        String Email = in.next();
        System.out.print("\nEnter Your Password: ");
        String Pass = in.next();
        System.out.print("\nEnter Your Address: ");
        String Add = in.next();
        while(!DB.Validate(Name, Email, Pass, Add)){
            Failure();
            System.out.println("____SIGN UP____");
            System.out.print("Enter Your Name: ");
            Name = in.next();
            System.out.print("\nEnter Your Email: ");
            Email = in.next();
            System.out.print("\nEnter Your Password: ");
            Pass = in.next();
            System.out.print("\nEnter Your Address: ");
            Add = in.next();
        }
        showMenu();
    }

    /**
     * this function will show the menu for the people who want to log in
     * @throws IOException
     */
    public void showLogIn() throws IOException {
        System.out.println("____LOG IN____");
        System.out.print("\nEnter Your Email: ");
        String Email = in.next();
        System.out.print("\nEnter Your Password: ");
        String Pass = in.next();
        Customer customer = DB.validateACC(Email, Pass);
        while(customer == null){
            Failure();
            System.out.println("ForgotPassword?");
            String Reply = in.next();
            if(Reply.equalsIgnoreCase("yes")){
                System.out.print("\nEnter Your Email: ");
                Email = in.next();
                System.out.print("\nEnter Your New Password: ");
                String NPass = in.next();
                String passR = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
                boolean p = NPass.matches(passR);
                while(!p){
                    System.out.println("Weak Password");
                    System.out.println("""
                            Password Should have:\s
                            1-At Least an Uppercase Letter
                            2-At least an Lowercase Letter
                            3-At Least one Digit
                            4-Length be 8 or More
                            5-No Special Characters""");
                    System.out.print("\nEnter Your New Password: ");
                    NPass = in.next();
                    p = NPass.matches(passR);
                }
                DB.savePassword(Email, NPass);
                showMenu();
                return;
            }
            else{
                System.out.println("____LOG IN____");
                System.out.print("\nEnter Your Email: ");
                Email = in.next();
                System.out.print("\nEnter Your Password: ");
                Pass = in.next();
                customer = DB.validateACC(Email, Pass);
            }
        }
        loggedIn(customer);
    }

    /**
     * this function will show the items in our catalog
     */
    public void showCatalog(){
        for(int i =0;i< DB.getCatalog().size();i++){
            Product P =new Product(DB.getCatalog().get(i));
            System.out.println("Product Number: " + (i+1));
            System.out.print("Product Name: ");
            System.out.print(P.getName());
            System.out.print("\nProduct Category: ");
            System.out.print(P.getCategory());
            if(P.getDiscount() != 0){
                double NewPrice = P.getPrice() - ((P.getDiscount() / 100) * P.getPrice());
                System.out.print("\nThere is a Discount! Product Price After Discount: ");
                System.out.print(NewPrice);
            }
            System.out.print("\nProduct Price: ");
            System.out.println(P.getPrice());
            System.out.println("----------------------------------------------------");

        }
    }

    /**
     * this function used if a failure happened in the system
     */
    public void Failure(){
        System.out.println("Action Couldn't Be Applied..Try Again");
    }
}