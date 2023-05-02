import java.util.Vector;

public class Catalog {
    Vector<Product>products;
    public Catalog(){
        this.products=new Vector<Product>();
    }
    public void showProducts(){
        for(Product p : products){
            System.out.println(p.getName() + "-"+ p.getPrice() + "-" + p.getCategory());
        }

    }

}
