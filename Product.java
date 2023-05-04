public class Product {
    protected double Price;
    protected String Name;
    protected String Category;
    protected double Discount;
    protected int ID;
    protected int Quantity;
    Product(){}
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
    public int getQuantity(){
        return Quantity;
    }


}
