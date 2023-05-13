/**
 * The Product class contains instance variables for the product's price, name, category, discount, ID, and quantity.The class provides constructors to create a product with all the instance variables specified, or to create a product by specifying only the name and quantity, and looking up the other instance variables from a Catalog object. The Product class also provides methods to get the product's name, category, price, discount, ID,and quantity, as well as a method to set the quantity of the product.
 */
public class Product {
    /**
     * price of the product
     */
    private final double Price;
    /**
     * name of the product
     */
    private final String Name;
    /**
     * Category of the product
     */
    private final String Category;
    /**
     * discount of the product
     */
    private final double Discount;
    /**
     * id of the product
     */
    private final int ID;
    /**
     * quantity of the product
     */
    private int Quantity;

    /**
     *constructor to set the data of the product
     * @param price
     * @param name
     * @param category
     * @param discount
     * @param id
     * @param Q
     */
    Product(double price,String name,String category,double discount ,int id,int Q)
    {
        Price = price;
        Name = name;
        Category = category;
        Discount = discount;
        ID = id;
        Quantity = Q;
    }

    /**
     *to get the rest of information of a product the user selected
     * @param name
     * @param quantity
     */
    Product(String name, int quantity){
        Product P = Catalog.catalog.get(0);
        for (int i = 0; i < Catalog.catalog.size(); i++) {
            P = Catalog.catalog.get(i);
            if(P.getName().equals(name)){
                break;
            }
        }
        this.Price = P.Price;
        this.Discount = P.Discount;
        this.ID = P.ID;
        this.Category = P.Category;
        this.Name = name;
        this.Quantity = quantity;
    }

    /**
     *copy constructor
     * @param other
     */
    public Product(Product other) {
        Price = other.Price;
        Name = other.Name;
        Category = other.Category;
        Discount = other.Discount;
        ID = other.ID;
        Quantity = other.Quantity;
    }
    /**
     * returns the name of the product
     * @return
     */
    public String getName(){
        return Name;
    }
    /**
     * returns the category of the product
     * @return
     */
    public String getCategory(){
        return Category;
    }
    /**
     * returns the price of the product
     * @return
     */
    public double getPrice (){
        return Price;
    }
    /**
     * returns the discount of the product
     * @return
     */
    public double getDiscount (){
        return Discount;
    }
    /**
     * returns the ID of the product
     * @return
     */
    public int getID (){
        return ID;
    }

    /**
     * returns the quantity of the product
     * @return
     */
    public int getQuantity (){
        return Quantity;
    }

    /**
     *this function will set the product quantity
     * @param quantity
     */
    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}