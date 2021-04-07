package DataAccessLayer;

import Model.Client;
import Connection.ConnectionFactory;

import java.sql.*;
import java.util.logging.Logger;

/**
 * This class is responsable for making the necessary operation to the Client database(insert,delete,display).
 * @author Roman Allexandru
 */
public class ClientDAO {

    protected static final Logger logger = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO client(id, name, city, deleted) values (?,?,?,?)";
    //private static final String deleteStatementString = "SELECT * from Client " +
                                                  //"where (deleted = '0') and (id = ?) ";

    private static final String deleteStatementString = "UPDATE `warehouse`.`client` SET `deleted` = 1 WHERE (`id` = ?);";

    /**
     * Basic constructor with no paramaters
     */
    public ClientDAO() {

    }

    /**
     * This method is used for inserting a client in the Client table from the database.
     * @param client the client to be inserted in the database.
     */
    public static void insertClient(Client client){

        Connection myConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        ResultSet rs = null;

        try {
            insertStatement = myConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, client.getId());
            insertStatement.setString(2, client.getNume());
            insertStatement.setString(3, client.getOras());
            insertStatement.setInt(4,0);

            int rowsAffected = insertStatement.executeUpdate();

            rs = insertStatement.getGeneratedKeys();

            System.out.println("(" + rowsAffected + ") rows affected");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is used for deleting a client in the Client table from the database.
     * It doesnt remove the client from the database, it switches it's flag named "deleted" on the value 1.
     * It prins a messaje with the rows affected.
     * @param client
     */
    public static void deleteClient(Client client){

        Connection myConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        ResultSet rs = null;


        try {

            insertStatement = myConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, client.getId());

            int rowsAffected = insertStatement.executeUpdate();
            rs = insertStatement.getGeneratedKeys();
            System.out.println("(" + rowsAffected + ") rows affected ");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method used for displaying the Client table in the console.
     */
    public static void display(){

        try {
        Connection myConnection = ConnectionFactory.getConnection();
        Statement stat = myConnection.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * from client");

        System.out.println("CLIENT");
        while(rs.next()){

            System.out.println(rs.getInt("id") + ", " + rs.getString("name") + ", " + rs.getString("city") + ", " + rs.getString("deleted"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();

    }


}
