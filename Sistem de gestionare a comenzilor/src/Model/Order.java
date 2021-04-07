package Model;
/**
 * Order is a simple class that stores the instance variable of an order.
 * @author Roman Allexandru
 */
public class Order {

    private int id;
    private String nume;
    private String produs;
    private int cantitate;

    /**
     * basic constructor for initialising the instance variables
     * @param id the order's id
     * @param nume the order's orderer's name
     * @param produs the order's name
     * @param cantitate the order's quantity
     */
    public Order(int id, String nume, String produs, int cantitate) {
        this.id = id;
        this.nume = nume;
        this.produs = produs;
        this.cantitate = cantitate;
    }

    /** overriden method for printing the instance variables
     *  @return the String to print
     */
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", produs='" + produs + '\'' +
                ", cantitate=" + cantitate +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getProdus() {
        return produs;
    }

    public int getCantitate() {
        return cantitate;
    }
}
