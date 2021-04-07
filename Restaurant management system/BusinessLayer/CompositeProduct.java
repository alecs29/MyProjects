package BusinessLayer;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem {

    private int id;
    private String name;
    private Double price;
    private ArrayList<MenuItem> subproducts;

    public CompositeProduct(int id, String name, Double price, ArrayList<MenuItem> list) {
        super();

        subproducts = list;
        this.id = id;
        this.name = name;
        this.price = price;
        this.composite = true;
    }

    public ArrayList<MenuItem> getSubproducts() {
        return subproducts;
    }



    public void add(MenuItem item){

        subproducts.add(item);
    }
    public void remove(MenuItem item){

        subproducts.remove(item);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setSubproducts(ArrayList<MenuItem> subproducts) {
        this.subproducts = subproducts;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }


}
