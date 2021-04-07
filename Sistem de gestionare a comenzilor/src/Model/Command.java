package Model;


/**
 * The class for storing commands using an Object instance variable and the use of
 * booleans variables that are used as flags to show the type of command.
 * @author Roman Alexandru
 */
public class Command {

    private Object tempCommand;
    private boolean insertClient = false;
    private boolean deleteClient = false;
    private boolean insertProduct = false;
    private boolean deleteProduct = false;
    private boolean makeOrder = false;
    private boolean makeReport = false;

    /**
     * Stores an object and sets the specific flag to true. The object should be
     * one of the four types: {Client, Product, Order, Report}.
     * @param object
     *        Stores the object.
     * @param typeOfcommand
     *        for setting a specific flag to true (should be between 1 and 6).
     *
     */
    public Command( Object object, int typeOfcommand){//, Client client, Product product, Order order, Report report){

        this.tempCommand = object;

        if(typeOfcommand == 1){
            insertClient = true;
        }
        else if(typeOfcommand == 2){
            deleteClient = true;
        }
        else if(typeOfcommand == 3){
            insertProduct = true;
        }
        else if(typeOfcommand == 4){
            deleteProduct = true;
        }
        else if(typeOfcommand == 5){
            makeOrder = true;
        }
        else if(typeOfcommand == 6){
            makeReport = true;
        }

    }


    public Object getTempCommand() {
        return tempCommand;
    }

    public boolean isInsertClient() {
        return insertClient;
    }

    public boolean isDeleteClient() {
        return deleteClient;
    }

    public boolean isInsertProduct() {
        return insertProduct;
    }

    public boolean isDeleteProduct() {
        return deleteProduct;
    }

    public boolean isMakeOrder() {
        return makeOrder;
    }

    public boolean isMakeReport() {
        return makeReport;
    }


}
