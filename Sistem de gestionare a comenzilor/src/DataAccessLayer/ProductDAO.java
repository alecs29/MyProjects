package DataAccessLayer;

import Model.Client;
import Model.Product;
import Connection.ConnectionFactory;

import java.sql.*;
import java.util.logging.Logger;

/**
 * This class is responsable for making the necessary operation to the Product database(insert,delete,display).
 * @author Roman Allexandru
 */
public class ProductDAO {

    protected static final Logger logger = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO product(id, quantity, productName, price, deleted) values (?,?,?,?,?)";
    private static final String deleteStatementString = "UPDATE `warehouse`.`product` SET `deleted` = 1 WHERE (`id` = ?);";
    private static final String updateQuantityString = "UPDATE `warehouse`.`product` SET `quantity` = ? WHERE (`id` = ?);";

    /**
     * Basic constructor with no paramaters
     */
    public ProductDAO() {
    }



    /**
     * This method is used for inserting a product in the Product table from the database.
     * @param product the client to be inserted in the database.
     */
    public static void insertProduct(Product product){

        Connection myConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        ResultSet rs = null;

        int afterId = verify(product);
        int cantitate = 0;
        if(afterId == -1) {//----------daca nu exista deja in tabel

            try {
                insertStatement = myConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
                insertStatement.setInt(1, product.getId());
                insertStatement.setInt(2, product.getCantitate());
                insertStatement.setString(3, product.getDenumire());
                insertStatement.setDouble(4, product.getPret());
                insertStatement.setInt(5, 0);

                int rowsAffected = insertStatement.executeUpdate();

                rs = insertStatement.getGeneratedKeys();

                System.out.println("(" + rowsAffected + ") rows affected");


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{//---------------daca deja exista updatam canntitatea
            try {
                myConnection = ConnectionFactory.getConnection();
                insertStatement = myConnection.prepareStatement("Select * from product where id = " + afterId);
                insertStatement.executeQuery();
                rs = insertStatement.getResultSet();
                if(rs.next()){
                    cantitate = rs.getInt(2);
                }
                myConnection = ConnectionFactory.getConnection();
                insertStatement = myConnection.prepareStatement(updateQuantityString, Statement.RETURN_GENERATED_KEYS);
                insertStatement.setInt(1, product.getCantitate() + cantitate);
                insertStatement.setInt(2, afterId);
                int rowsAffected = insertStatement.executeUpdate();
                System.out.println("(" + rowsAffected + ") rows affected");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * method for verifying if the product already exists in the database.
     * @param product the product to verify.
     * @return -1 in case it's not in the database or the id of the given product in alternative.
     */
    public static int verify(Product product){

        int verifiedId = -1;
        try {
            Connection myConnection = ConnectionFactory.getConnection();
            Statement stat = myConnection.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * from product");

            while(rs.next()){

                if(product.getDenumire().equals(rs.getString("productName"))){

                    verifiedId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return verifiedId;
    }

    /**
     * This method is used for deleting a product in the Product table from the database.
     * It doesnt remove the product from the database, it switches it's flag named "deleted" on the value 1.
     * It prins a messaje with the rows affected.
     * @param product
     */
    public static void deleteProduct(Product product){

        Connection myConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        ResultSet rs = null;


        try {

            insertStatement = myConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, product.getId());

            int rowsAffected = insertStatement.executeUpdate();
            rs = insertStatement.getGeneratedKeys();
            System.out.println("(" + rowsAffected + ") rows affected ");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method used for displaying the Product table in the console
     */
    public static void display(){

        try {
            Connection myConnection = ConnectionFactory.getConnection();
            Statement stat = myConnection.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * from product");

            System.out.println("PRODUCT");
            while(rs.next()){

                System.out.println(rs.getInt("id") + ", " + rs.getString("quantity") + ", " + rs.getString("productName") + ", " + rs.getDouble("price")+ ", " + rs.getString("deleted"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();

    }


}
