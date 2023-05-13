import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Cart class is a class that represents a shopping cart The class contains methods for adding items to the cart, viewing the cart, saving the cart, loading a saved cart, and clearing the cart.The cart is implemented as a HashMap where the keys are Product objects and the values are integers representing the quantity of each product in the cart.The Cart class also interacts with a CSV file to save and load cart data for each customer account.
 */
public class Cart {
    /**
     * will be filled with the items the customer will choose to put them in the cart
     */
    private static final Map<Product,Integer> cart = new HashMap<>();

    /**
     * will add item in the cart
     * @param product
     * @param quantity
     */
    public void addItem(Product product, Integer quantity) {
        for (Map.Entry<Product, Integer> pair : cart.entrySet()) {
            if(product.getID() == pair.getKey().getID()){
                pair.setValue(pair.getValue() + quantity);
                return;
            }
        }
        cart.put(product,quantity);

    }

    /**
     *will empty the cart when the order is delivered
     * @param C
     */
    public void clearCart(Customer C) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Accounts-Carts.csv"));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (C.getEmail().equals(parts[0])) {
                    line = parts[0] + "," + parts[1];
                    lines.add(line);
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
        cart.clear();
    }

    /**
     * will view the cart content to the user
     */
    public void viewCart(){
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            System.out.println(entry.getKey().getName() + " = " + entry.getValue());
        }
    }

    /**
     *will save the cart products in the system as not delivered order
     * @param C
     */
    public void saveCart(Customer C){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Accounts-Carts.csv"));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (C.getEmail().equals(values[0])) {
                    StringBuilder sb = new StringBuilder(line);
                    for (Map.Entry<Product, Integer> entry : C.custCart.getCart().entrySet()) {
                        Product product = entry.getKey();
                        int quantity = entry.getValue();
                        sb.append(",");
                        sb.append(product.getName());
                        sb.append(",");
                        sb.append(quantity);
                    }
                    lines.add(sb.toString());
                } else {
                    lines.add(line);
                }
            }
            reader.close();

            PrintWriter writer = new PrintWriter(new FileWriter("Accounts-Carts.csv", false));
            for (String newLine : lines) {
                if(newLine != null)writer.println(newLine);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    /**
     *will fill the cart with items the user selected before
     * @param customer
     */
    public void loadCart(Customer customer){
        try (BufferedReader reader = new BufferedReader(new FileReader("Accounts-Carts.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (customer.getEmail().equals(parts[0])) {
                    int check = 0;
                    for (int i = 2; i < parts.length; i += 2) {
                        String productName = parts[i];
                        int quantity = Integer.parseInt(parts[i + 1]);
                        Product product = new Product(productName, quantity);
                        customer.custCart.addItem(product, quantity);
                        check =1;
                    }
                    if(check == 0){
                        customer.custCart.clearCart(customer);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    /**
     *returns our cart
     * @return
     */
    public Map<Product, Integer> getCart() {
        return cart;
    }
}