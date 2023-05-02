import java.util.Vector;

public class Order {
    private int Id;
    private String Status;
    private String DeliveryDate;
    private String OrderedDate;
    private String PaymentMethod;
    private double TotalPrice;
    private Vector<Product> OProducts;
    public String GetDDate(){
        return DeliveryDate;
    }
}
