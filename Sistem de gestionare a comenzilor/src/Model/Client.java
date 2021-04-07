package Model;

/**
 * Client is a simple class that stores the instance variable of a client.
 * @author Roman Allexandru
 */
public class Client {

    private int id;
    private String nume;
    private String oras;

    /**
     * basic constructor for initialising the instance variables
     * @param id the client id number
     * @param nume the client's name
     * @param oras the client's city
     */
    public Client(int id, String nume, String oras) {
        this.id = id;
        this.nume = nume;
        this.oras = oras;
    }

    /** overriden method for printing the instance variables
     *  @return the String to print
     */

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", nume='" + nume + '\'' +
                ", oras='" + oras + '\'' +
                '}';
    }



    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getOras() {
        return oras;
    }
}
