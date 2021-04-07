package Model;
/**
 * Product is a simple class that stores the instance variable of a product.
 * @author Roman Allexandru
 */
public class Product {

    private int id;
    private String denumire;
    private int cantitate;
    private double pret;


    /**
     * basic constructor for initialising the instance variables
     * @param id the product's id
     * @param denumire the product's name
     * @param cantitate the product's quantity
     * @param pret the product's price
     */
    public Product(int id, String denumire, int cantitate, double pret) {
        this.id = id;
        this.denumire = denumire;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    /** overriden method for printing the instance variables
     *  @return the String to print
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", cantitate=" + cantitate +
                ", pret=" + pret +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }

    public int getCantitate() {
        return cantitate;
    }

    public double getPret() {
        return pret;
    }
}
