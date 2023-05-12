import java.io.*;
import java.util.*;

public class Database {
    Scanner in = new Scanner(System.in);

    /**
     *
     * @param Name
     * @param Email
     * @param Pass
     * @param Add
     * @return
     * @throws IOException
     */
    //OTP Sending Operation doesn't work on all laptops due to settings changes.
    //It may be fixed if Hotspot is used instead of Wi-Fi
    public boolean Validate(String Name, String Email, String Pass, String Add) throws IOException{
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
            int otpCode = random.nextInt(100,999);
            String S_otpCode = Integer.toString(otpCode);
            OTP sendotp = new OTP();
            sendotp.sendOTPEmail(Email, S_otpCode);
            System.out.print("Enter the OTP code you received: ");
            String entered_OTP = in.next();
            if(entered_OTP.equals(S_otpCode)){
                FileWriter writer = new FileWriter("Accounts-Carts.csv", true);
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
    public void saveOrder(Order order) throws IOException {
        if(order != null){
            boolean idSet = false;
            while(!idSet){
                Random random = new Random();
                int orderId = random.nextInt(100,999);
                try{
                    BufferedReader reader1 = new BufferedReader(new FileReader("NDeliveredOrders.csv"));
                    String line1;
                    while ((line1 = reader1.readLine()) != null) {
                        String[] values = line1.split(",");
                        if (Integer.toString(order.getID()).equals(values[0])) {
                            break;
                        }
                    }
                    reader1.close();
                    BufferedReader reader2 = new BufferedReader(new FileReader("DeliveredOrders.csv"));
                    String line2;
                    while ((line2 = reader2.readLine()) != null) {
                        String[] values = line2.split(",");
                        if (Integer.toString(order.getID()).equals(values[0])) {
                            break;
                        }
                    }
                    reader2.close();
                    idSet = true;
                    order.setID(orderId);
                }catch (IOException e) {
                    System.out.println("An error occurred while reading the CSV file: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            String line = order.getID() + "," + order.getDDate() + "," + order.getPMethod() +
                    "," + order.getTotalPrice();
            FileWriter writer = new FileWriter("NDeliveredOrders.csv", true);
            writer.write(line + "\n");
            writer.flush();
            writer.close();
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
    public void removeOrder(String Date) throws IOException{
        try {
            String fileName = "NDeliveredOrders.csv";
            String line ="";
            String deliveredOrders = "";
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                String[] fields = line.split(",");
                if (fields[1].equals(Date)) {
                    deliveredOrders += line + "\n";
                    try{
                        BufferedReader reader1 = new BufferedReader(new FileReader("NDeliveredOrders.csv"));
                        List<String> lines = new ArrayList<>();
                        String line2;
                        while ((line2 = reader1.readLine()) != null) {
                            if (line.equals(line2)) {
                                continue;
                            } else {
                                lines.add(line2);
                            }
                        }
                        reader1.close();
                        PrintWriter writer = new PrintWriter(new FileWriter("NDeliveredOrders.csv", false));
                        for (String newLine : lines) {
                            writer.println(newLine);
                        }
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred while writing to the file: " + e.getMessage());
                    }
                    break;
                }
            }
            reader.close();
            FileWriter deliveredWriter = new FileWriter("DeliveredOrders.csv", true);
            deliveredWriter.write(deliveredOrders);
            deliveredWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading or writing the file: " + e.getMessage());
            e.printStackTrace();
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
            Scanner sc = new Scanner(new File("Accounts-Carts.csv"));
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
            BufferedReader reader = new BufferedReader(new FileReader("Accounts-Carts.csv"));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Email.equals(parts[0])) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < parts.length; i++) {
                        if(i == 1){
                            sb.append(NPass);
                        }
                        else{
                            sb.append(parts[i]);
                        }
                        if(i != parts.length-1) sb.append(",");
                    }
                    lines.add(sb.toString());
                    changed = 1;
                } else {
                    lines.add(line);
                }
            }
            reader.close();

            PrintWriter writer = new PrintWriter(new FileWriter("Accounts-Carts.csv", false));
            for (String newLine : lines) {
                writer.println(newLine);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
        if(changed == 0) System.out.println("There is no such an email to change password");
        else System.out.println("Password Changed Successfully");
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
