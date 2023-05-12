import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private static final Map<Product,Integer> cart = new HashMap<>();
    /**
     *
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
     *
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

    public void viewCart(){
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            System.out.println(entry.getKey().getName() + " = " + entry.getValue());
        }
    }

    /**
     *
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
     *
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
     *
     * @return
     */
    public Map<Product, Integer> getCart() {
        return cart;
    }
}