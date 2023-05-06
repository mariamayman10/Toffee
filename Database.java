import java.util.Random;
import java.util.Vector;
public class Database {
    private static final Vector<Customer> RegC =  new Vector<>();
    private final Vector<Order> DeliveredO = new Vector<>();
    private final Vector<Order> NDeliveredO = new Vector<>();
    public boolean Validate(String Name, String Email, String Pass, String Add){
        String NameR = "^[a-zA-Z\\s]+$";
        boolean n = Name.matches(NameR);
        String EmailR = "^[\\w-.]+@gmail\\.com$";
        boolean e = Email.matches(EmailR);
        String PassR = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        boolean p = Pass.matches(PassR);
        if(!n) System.out.println("Invalid Name");
        if(!e) System.out.println("Invalid Email");
        if(!p) System.out.println("Weak Password");
        if(n && e && p){
            Customer NewC = new Customer();
            NewC.setPassword(Pass);
            NewC.setAddress(Add);
            NewC.setEmail(Email);
            RegC.add(NewC);
        }
        return n && e && p;
    }
    public void SaveOrder(Order order){
        if(order != null){
            boolean IDSet = false;
            while(!IDSet){
                Random random = new Random();
                int OrderId = random.nextInt(100);
                for (Order O:NDeliveredO){
                    if(OrderId == O.getID()){
                        break;
                    }
                }
                for (Order O:DeliveredO){
                    if(OrderId == O.getID()){
                        break;
                    }
                }
                order.setID(OrderId);
                IDSet = true;
            }
            NDeliveredO.add(order);
            System.out.println("Your Order is Placed Successfully With ID " + order.getID());
        }
    }
    public boolean Update_catalog(Product P, int Q){
        for (Product product : getCatalog()) {
            if(product.getID() == P.getID()){
                if(product.getQuantity() - Q < 0){
                    System.out.print("No enough Quantity..Only " + product.getQuantity() + " items of this product is valid");
                    return false;
                }
                product.setQuantity(product.getQuantity() - Q);
                break;
            }
        }
        return true;
    }
    public void RemoveOrder(String Date){
        for (int i = 0;i < NDeliveredO.size();i++){
            Order O = NDeliveredO.get(i);
            if(O.GetDDate().equals(Date)){
                NDeliveredO.remove(i);
                DeliveredO.add(O);
            }
        }
    }
    public Customer ValidateAcc(String Email, String Pass){
        for (Customer RC : RegC) {
            if (RC.getEmail().equals(Email) && RC.getPassword().equals(Pass)){
                return RC;
            }
        }
        System.out.println("Invalid Account");
        return null;
    }
    public void SavePassword(String Email, String NPass){
        int changed = 0;
        for (Customer RC : RegC) {
            if (RC.getEmail().equals(Email)){
                RC.setPassword(NPass);
                changed = 1;
            }
        }
        if(changed == 0) System.out.println("There is no such an email to change password");
    }
    public Vector<Product> getCatalog(){
        return Catalog.catalog;
    }
    public Product SearchforProduct(int productId){
        for (int i = 0;i < getCatalog().size();i++){
            if(getCatalog().get(i).getID() == productId){
                return getCatalog().get(i);
            }
        }
        return null;
    }
}
