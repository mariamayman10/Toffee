public class Product {
    private double Price;
    private String Name;
    private String Category;
    private double Discount;
    private int ID;
    private int Quantity;
    Product(double price,String name,String category,double discount ,int id,int Q)
    {
        Price = price;
        Name = name;
        Category = category;
        Discount = discount;
        ID = id;
        Quantity = Q;
    }
    public Product(Product other) {
        Price = other.Price;
        Name = other.Name;
        Category = other.Category;
        Discount = other.Discount;
        ID = other.ID;
        Quantity = other.Quantity;
    }
    public String getName(){
        return Name;
    }
    public String getCategory(){
        return Category;
    }
    public double getPrice (){
        return Price;
    }
    public double getDiscount (){
        return Discount;
    }
    public int getID (){
        return ID;
    }
    public int getQuantity (){
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
