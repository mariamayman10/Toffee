import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.io.FileWriter;

public class Database {
    private final Vector<Order> deliveredO = new Vector<>();
    private final Vector<Order> NDeliveredO = new Vector<>();

    /**
     *
     * @param Name
     * @param Email
     * @param Pass
     * @param Add
     * @return
     * @throws IOException
     */
    public boolean Validate(String Name, String Email, String Pass, String Add) throws IOException {
        String nameR = "^[a-zA-Z\\s]+$";
        boolean n = Name.matches(nameR);
        String emailR = "^[\\w-.]+@gmail\\.com$";
        boolean e = Email.matches(emailR);
        String passR = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        boolean p = Pass.matches(passR);
        if(!n) System.out.println("Invalid Name");
        if(!e) System.out.println("Invalid Email");
        if(!p) System.out.println("Weak Password");
        if(n && e && p){
            Customer newC = new Customer();
            newC.setPassword(Pass);
            newC.setAddress(Add);
            newC.setEmail(Email);
            FileWriter writer = new FileWriter("Test.csv", true);
            writer.write(newC.getEmail() + ','+newC.getPassword() + "\n\r");
            writer.flush();
            writer.close();
        }
        return n && e && p;
    }

    /**
     *
     * @param order
     */
    public void saveOrder(Order order){
        if(order != null){
            boolean idSet = false;
            while(!idSet){
                Random random = new Random();
                int orderId = random.nextInt(100);
                for (Order O:NDeliveredO){
                    if(orderId == O.getID()){
                        break;
                    }
                }
                for (Order O: deliveredO){
                    if(orderId == O.getID()){
                        break;
                    }
                }
                order.setID(orderId);
                idSet = true;
            }
            NDeliveredO.add(order);
            System.out.println("Your Order is Placed Successfully With ID " + order.getID() + '\n');
        }
    }

    /**
     *
     * @param P
     * @param Q
     * @return
     */
    public boolean updateCatalog(Product P, int Q){
        for (Product product : getCatalog()) {
            if(product.getID() == P.getID()){
                if(product.getQuantity() - Q < 0){
                    System.out.println("No enough Quantity.. " + product.getQuantity() + " items of this product is valid");
                    return false;
                }
                product.setQuantity(product.getQuantity() - Q);
                break;
            }
        }
        return true;
    }

    /**
     *
     * @param Date
     */
    public void removeOrder(String Date){
        for (int i = 0;i < NDeliveredO.size();i++){
            Order O = NDeliveredO.get(i);
            if(O.getDDate().equals(Date)){
                NDeliveredO.remove(i);
                deliveredO.add(O);
            }
        }
    }

    /**
     *
     * @param Email
     * @param Pass
     * @return
     */
    public Customer validateACC(String Email, String Pass){
        try{
            Scanner sc = new Scanner(new File("Test.csv"));
            sc.useDelimiter(",|\\n");
            while (sc.hasNext()) {
                String email = sc.next();
                if (sc.hasNext()) {
                    if (email.equals("\r")) email = sc.next();
                    String pass = sc.next();
                    while (!Character.isLetter(pass.charAt(pass.length() - 1)) && pass.length() >= 8 && !Character.isDigit(pass.charAt(pass.length() - 1))) {
                        pass = pass.substring(0, pass.length() - 1);
                    }
                    if (email.equals(Email) && pass.equals(Pass)) {
                        Customer RC = new Customer();
                        RC.setEmail(email);
                        RC.custCart.loadCart(RC);
                        return RC;
                    }
                }
            }
            sc.close();

        }catch (IOException e) {
            System.out.println("An error occurred while reading the CSV file: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Invalid Account");
        return null;
    }

    /**
     *
     * @param Email
     * @param NPass
     */
    public void savePassword(String Email, String NPass){
        int changed = 0;
        try{
            Scanner sc = new Scanner(new File("C:\\Users\\DELL 5480\\IdeaProjects\\untitled\\Test.csv"));
            //parsing a CSV file into the constructor of Scanner class
            sc.useDelimiter(",");
            //setting comma as delimiter pattern

            while (sc.hasNext()) {
                String email , pass;
                email = sc.next();
                pass = sc.next();
                if (Objects.equals(email, Email)){
                    pass = NPass;
                    changed = 1;
                }
            }
            sc.close();
        }catch(IOException e){
            System.out.println("An error occurred while reading the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        if(changed == 0) System.out.println("There is no such an email to change password");
    }

    /**
     *
     * @return
     */
    public Vector<Product> getCatalog(){
        return Catalog.catalog;
    }

    /**
     *
     * @param productId
     * @return
     */
    public Product searchForProduct(int productId){
        for (int i = 0;i < getCatalog().size();i++){
            if(getCatalog().get(i).getID() == productId){
                return getCatalog().get(i);
            }
        }
        return null;
    }
}