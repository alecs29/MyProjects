package DataAccessLayer;

import Model.Client;
import Model.Order;
import Connection.ConnectionFactory;

import java.sql.*;
import java.util.logging.Logger;

/**
 * This class is responsable for making the necessary operation to the Order database(make order,display).
 * @author Roman Allexandru
 */
public class OrderDAO {

    protected static final Logger logger = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO orders(orderId, orderName, orderProduct, orderQuantity) values (?,?,?,?)";
    private static final String updateQuantityString = "UPDATE `warehouse`.`product` SET `quantity` = ? WHERE (`id` = ?);";

    /**
     * Basic constructor with no paramaters
     */
    public OrderDAO() {

    }

    /**
     * This method is used for inserting an order in the Order table from the database.
     * @param order the order to be inserted in the database.
     */
    public static void makeOrder(Order order){

        Connection myConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        ResultSet rs = null;

        try {
            insertStatement = myConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, order.getId());
            insertStatement.setString(2, order.getNume());
            insertStatement.setString(3, order.getProdus());
            insertStatement.setInt(4, order.getCantitate());

            int rowsAffected = insertStatement.executeUpdate();

            rs = insertStatement.getGeneratedKeys();

            update(order);

            System.out.println("(" + rowsAffected + ") rows affected");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Updates the product table after an order
     * @param order the order made
     */
    public static void update(Order order){

        Connection myConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        ResultSet rs = null;

        int cantitatePeStoc = 0;
        int id = 0;

        try {
            myConnection = ConnectionFactory.getConnection();
            Statement stat = myConnection.createStatement();
            rs = stat.executeQuery("SELECT * from product");
            while(rs.next()){

                String str = rs.getString("productName");
                if(order.getProdus().equals(str)){

                    cantitatePeStoc = rs.getInt(2);
                    id = rs.getInt(1);
                }
            }

            myConnection = ConnectionFactory.getConnection();
            insertStatement = myConnection.prepareStatement(updateQuantityString, Statement.RETURN_GENERATED_KEYS);
            int aux = cantitatePeStoc - order.getCantitate();
            insertStatement.setInt(1,  aux);
            insertStatement.setInt(2, id);
            int rowsAffected = insertStatement.executeUpdate();
            System.out.println("(" + rowsAffected + ") rows affected");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used for displaying the Orders table in the console.
     */
    public static void display(){

        try {
            Connection myConnection = ConnectionFactory.getConnection();
            Statement stat = myConnection.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * from orders");

            System.out.println("ORDER");
            while(rs.next()){

                System.out.println(rs.getInt("orderId") + ", " + rs.getString("orderName") + ", " + rs.getString("orderProduct") + ", " + rs.getDouble("orderQuantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();

    }


}
