import java.util.Objects;
import java.util.Vector;
public class Database {
    private static Vector<Customer> RegC =  new Vector<Customer>();
    private Vector<Order> DeliveredO = new Vector<Order>();
    private Vector<Order> NDeliveredO = new Vector<Order>();
    public boolean Validate(String Name, String Email, String Pass, String Add){
        String NameR = "^[a-zA-Z\\s]+$";
        boolean n = Name.matches(NameR);
        String EmailR = "^[\\w-.]+@gmail\\.com$";
        boolean e = Email.matches(EmailR);
        //String PassR = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        //boolean p = Pass.matches(PassR);
        if(!n) System.out.println("Invalid Name");
        if(!e) System.out.println("Invalid Email");
        //if(!p) System.out.println("Weak Password");
        if(n && e){
            Customer NewC = new Customer();
            NewC.setPassword(Pass);
            NewC.setAddress(Add);
            NewC.setEmail(Email);
            RegC.add(NewC);
        }
        return n && e ;
    }
    public void SaveOrder(Order order){
        NDeliveredO.add(order);
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
        Customer cust = new Customer();
        return cust;
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
        Catalog catalog = new Catalog();
        return catalog.catalog;
    }
}
