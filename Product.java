public class Product {
    protected double Price;
    protected String Name;
    protected String Category;
    protected double Discount;
    protected int ID;
    Product(double price,String name,String category,double discount ,int id)
    {
        Price = price;
        Name = name;
        Category = category;
        Discount = discount;
        ID = id;
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
    
}
