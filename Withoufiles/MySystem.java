import java.util.Scanner;

public class MySystem {
    Scanner in = new Scanner(System.in);
    Database DB = new Database();
    public void EnterApplication(){
        System.out.println("_________Welcome to Toffee_________");
    }
    public void LoggedIn(Customer customer){
        while(true) {
            System.out.println("Want: 1)View Products 2)Add Item To Cart 3)Place Order 4)Log Out");
            int choice = in.nextInt();
            while (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
                System.out.println("Please Choose Either 1, 2 , 3 or 4");
                choice = in.nextInt();
            }
            if (choice == 1) ShowCatalog();
            else if (choice == 2) {
                System.out.println("Please Enter the number of the product: ");
                int Pid = in.nextInt();
                System.out.println("Please Enter the quantity of the product: ");
                int Quantity = in.nextInt();
                if(DB.SearchforProduct(Pid) == null) System.out.println("There is no Such a Product");
                else {
                    Product Product = new Product(DB.SearchforProduct(Pid));
                    if (DB.Update_catalog(Product, Quantity)) customer.custCart.addItem(Product, Quantity);
                }
            } else if (choice == 3) {
                DB.SaveOrder(customer.Checkout());
            }
            else break;
        }
    }
    public void ShowMenu(){
        while(true){
            System.out.println("Go to: 1)SignUp 2)Login 3)View Products 4)Exit");
            int choice = in.nextInt();
            while (choice != 1 && choice != 2 && choice != 3 && choice != 4){
                System.out.println("Please Choose Either 1, 2 , 3 or 4");
                choice = in.nextInt();
            }
            if(choice == 1)ShowSignUp();
            else if(choice == 2)ShowLogIn();
            else if(choice == 3) {
                ShowCatalog();
                ShowMenu();
            }
            else System.exit(0);
        }
    }
    public void ShowSignUp(){
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
        ShowMenu();
    }
    public void ShowLogIn(){
        System.out.println("____LOG IN____");
        System.out.print("\nEnter Your Email: ");
        String Email = in.next();
        System.out.print("\nEnter Your Password: ");
        String Pass = in.next();
        Customer customer = DB.ValidateAcc(Email, Pass);
        while(customer == null){
            Failure();
            System.out.println("ForgotPassword?");
            String Reply = in.next();
            if(Reply.equalsIgnoreCase("yes")){
                System.out.print("\nEnter Your Email: ");
                Email = in.next();
                System.out.print("\nEnter Your New Password: ");
                String NPass = in.next();
                DB.SavePassword(Email, NPass);
            }
            else{
                System.out.println("____LOG IN____");
                System.out.print("\nEnter Your Email: ");
                Email = in.next();
                System.out.print("\nEnter Your Password: ");
                Pass = in.next();
            }
            customer = DB.ValidateAcc(Email, Pass);
        }
        LoggedIn(customer);
    }
    public void ShowCatalog(){
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
    public void Failure(){
        System.out.println("Action Couldn't Be Applied..Try Again");
    }
}
