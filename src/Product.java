public class Product {
    private final double Price;
    private final String Name;
    private final String Category;
    private final double Discount;
    private final int ID;
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