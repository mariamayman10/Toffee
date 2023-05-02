import java.util.Scanner;

public class MySystem {
    Scanner in = new Scanner(System.in);
    Database DB;
    public void EnterApplication(){
        System.out.println("_________Welcome to Toffee_________");

    }
    public void ShowMenu(){
        System.out.println("Go to: 1)SignUp 2)Login 3)View Products");
        int choice = in.nextInt();
        while (choice != 1 && choice != 2 && choice != 3){
            System.out.println("Please Choose Either 1, 2 or 3");
            choice = in.nextInt();
        }
        if(choice == 1)ShowSignUp();
        else if(choice == 2)ShowLogIn();
        else ShowCatalog();
    }
    public void ShowSignUp(){
        System.out.println("____SIGN UP____");
        System.out.print("Enter Your Name: ");
        String Name = in.next();
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
    }
    public void ShowLogIn(){
        System.out.println("____LOG IN____");
        System.out.print("\nEnter Your Email: ");
        String Email = in.next();
        System.out.print("\nEnter Your Password: ");
        String Pass = in.next();
        while(!DB.ValidateAcc(Email, Pass)){
            Failure();
            System.out.println("ForgotPassword?");
            String Reply = in.next();
            if(Reply.toLowerCase().equals("yes")){
                System.out.print("\nEnter Your Email: ");
                Email = in.next();
                System.out.print("\nEnter Your New Password: ");
                String NPass = in.next();
                DB.SavePassword(Email, NPass);
            }
            System.out.println("____LOG IN____");
            System.out.print("\nEnter Your Email: ");
            Email = in.next();
            System.out.print("\nEnter Your Password: ");
            Pass = in.next();
        }
    }
    public void ShowCatalog(){
        for (int i = 0;i < DB.Catalog.size();i++){
            Product P = DB.Catalog.get(i);
            System.out.print("Product Name: ");
            P.getName();
            System.out.print("\nProduct Category: ");
            P.getCategory();
            if(P.getDiscount() != 0){
                double NewPrice = P.getPrice() - ((P.getDiscount() / 100) * P.getPrice());
                System.out.print("\nThere is a Discount! Product Price After Discount: ");
                P.getPrice();
            }
            System.out.print("\nProduct Price: ");
            P.getPrice();
        }
    }
    public void Failure(){
        System.out.println("Action Couldn't Be Applied..Try Again");
    }
}
