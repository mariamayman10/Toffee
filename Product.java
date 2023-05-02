public class Product {
    private double Price;
    private int Discount;
    private int Id;
    String Name;
    String Category;

    public int getDiscount() {
        return Discount;
    }
    public int getId() {
        return Id;
    }
    public double getPrice() {
        return Price;
    }
    public String getCategory() {
        return Category;
    }
    public String getName() {
        return Name;
    }
    public void setDiscount(int discount) {
        Discount = discount;
    }
    public void setPrice(int price) {
        Price = price;
    }
    Product(int id, int discount, double price, String name, String category){
        Id = id;Discount = discount;Price = price;Name = name;Category = category;
    }
}
