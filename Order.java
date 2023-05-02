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
    public int getId() {
        return Id;
    }
    public double getTotalPrice() {
        return TotalPrice;
    }
    public String getODate() {
        return OrderedDate;
    }
    public String getPaymentMethod() {
        return PaymentMethod;
    }
    public String getStatus() {
        return Status;
    }
    public void setDDate(String deliveryDate) {
        DeliveryDate = deliveryDate;
    }
    public void setOProducts(Vector<Product> OProducts) {
        this.OProducts = OProducts;
    }
    public void setDate(String orderedDate) {
        OrderedDate = orderedDate;
    }
    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }
    public void setStatus(String status) {
        Status = status;
    }
    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }
    public void setID(int id) {
        Id = id;
    }
}
