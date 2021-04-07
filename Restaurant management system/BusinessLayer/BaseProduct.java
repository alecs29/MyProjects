package BusinessLayer;


public class BaseProduct extends MenuItem {

    private int id;
    private String name;
    private Double price;

    public BaseProduct(int id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.base = true;

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

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }


}
