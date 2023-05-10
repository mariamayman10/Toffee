import java.io.*;
import java.util.*;

public class Database {
    Scanner in = new Scanner(System.in);
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
    public boolean Validate(String Name, String Email, String Pass, String Add) throws IOException, InterruptedException {
        String nameR = "^[a-zA-Z\\s]+$";
        boolean n = Name.matches(nameR);
        String emailR = "^[\\w-.]+@gmail\\.com$";
        boolean e = Email.matches(emailR);
        String passR = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        boolean p = Pass.matches(passR);
        if(!n) System.out.println("Invalid Name");
        if(!e) System.out.println("Invalid Email");
        if(!p) {
            System.out.println("Weak Password");
            System.out.println("""
                            Password Should have:\s
                            1-At Least an Uppercase Letter
                            2-At least an Lowercase Letter
                            3-At Least one Digit
                            4-Length be 8 or More
                            5-No Special Characters""");
        }
        if(n && e && p){
            Customer newC = new Customer();
            newC.setPassword(Pass);
            newC.setAddress(Add);
            newC.setEmail(Email);
            Random random = new Random();
            int OTP = random.nextInt(1000, 5000);
            String S_otp = Integer.toString(OTP);
            String backspace = "";
            System.out.println("This is the sent OTP..Remember it..Will disappear after 2 seconds");
            for (int i = 0; i < S_otp.length(); i++) {
                backspace += "\b";
            }
            System.out.print(S_otp);
            Thread.sleep(2000);
            System.out.print(backspace);
            System.out.println("Enter the received OTP: ");
            int otp = in.nextInt();
            if(otp == OTP){
                FileWriter writer = new FileWriter("Test.csv", true);
                writer.write(newC.getEmail() + ','+newC.getPassword() + "\n\r");
                writer.flush();
                writer.close();
                System.out.println("\nAccount Saved Successfully");
            }
            else{
                System.out.println("\nInvalid OTP..Can't Register the Account");
            }
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
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Test.csv"));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Email.equals(parts[0])) {
                    line = "";
                    for (int i = 0; i < parts.length; i++) {
                        if(i == 1){
                            line += NPass;
                        }
                        else{
                            line += parts[i];
                        }
                        if(i != parts.length-1) line += ",";
                    }
                    lines.add(line);
                    changed = 1;
                } else {
                    lines.add(line);
                }
            }
            reader.close();

            PrintWriter writer = new PrintWriter(new FileWriter("Test.csv", false));
            for (String newLine : lines) {
                writer.println(newLine);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
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
