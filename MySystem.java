import javax.naming.Name;
import java.sql.SQLOutput;
import java.util.Scanner;

public class MySystem {
    Scanner in = new Scanner(System.in);
    Database DB = new Database();
    public void EnterApplication(){
        System.out.println("_________Welcome to Toffee_________");

    }
    /////////////////////////////////////////(last)
    public void LoggedIn(Customer cust){

    }
    public void ShowMenu(){
        while(true){
            System.out.println("Go to: 1)SignUp 2)Login 3)View Products 4)Exit");
            int choice = in.nextInt();
            while (choice != 1 && choice != 2 && choice != 3 && choice != 4 ){
                System.out.println("Please Choose Either 1, 2 , 3 or 4");
                choice = in.nextInt();
            }
            if(choice == 1)ShowSignUp();
            else if(choice == 2)ShowLogIn();
            else if(choice == 3) ShowCatalog();
            else break;
        }
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
        Customer cust = DB.ValidateAcc(Email, Pass);
        while(cust!=null){
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

        LoggedIn(cust);

    }
    public void ShowCatalog(){
       for(int i =0;i< DB.getCatalog().size();i++){
           Product P =new Product(DB.getCatalog().get(i));
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
