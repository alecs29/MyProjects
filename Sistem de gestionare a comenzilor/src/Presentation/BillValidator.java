package Presentation;

import Model.Order;
import Connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A class that validates an order.
 *
 */
public class BillValidator {

    /**
     * Uses the validate method for validating the order and handles the SQLException.
     * @param order the order object given for validation.
     */
    public BillValidator(Order order) {

        try {
            validate(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method receives an order and checks if the client who made the order exists,
     *                                          if the product ordered exists,
     *                                          and if the quantity ordered is smaller then the existent quantity fom database.
     * @param order the specific order (instance of Order).
     * @return the String than can be : "ok" if everithings fine, or a specific message if an error is found  in the order made.

     * @throws SQLException if an error occured in getting the information from the database.
     */
    public static String validate(Order order) throws SQLException {

        String mesaj = "ok";

        ResultSet clientRs = returnRs("client");
        ResultSet productRs = returnRs("product");

        boolean ok1 = false;
        while(clientRs.next()){

            if(order.getNume().equals(clientRs.getString("name"))){
                ok1 = true;
            }

        }
        if(ok1 == false)
            mesaj = "clientul nu exista in magazin!";

        else {

            boolean ok2 = false;
            boolean ok3 = true;
            int cantitateExistanta = 0;
            while (productRs.next()) {

                if (order.getProdus().equals(productRs.getString("productName"))) {

                    ok2 = true;

                    cantitateExistanta = productRs.getInt("quantity");
                    if (order.getCantitate() > cantitateExistanta) {
                        ok3 = false;
                    }
                }


            }
            if (ok2 == false)
                mesaj = "produsul nu exista pe stoc!\n";
            else if (ok3 == false)
                mesaj = "Stoc insuficient! Doriti "+ order.getCantitate() + " " + order.getProdus() + ". Exista doar " + cantitateExistanta + " pe stoc.\n" ;


        }

        return mesaj;
    }

    /**
     * Returns a ResultSet for the "SELECT * table" query, table being given as string.
     * @param table the name of the table given.
     * @return the ResultSet for the SELECT * table query.
     */
    public static ResultSet returnRs(String table){

        ResultSet rs = null;
        try {
            Connection myConnection = ConnectionFactory.getConnection();
            Statement stat = myConnection.createStatement();
            rs = stat.executeQuery("SELECT * from " + table);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;

    }


    /**
     * Method used for searching the price of a product that has been ordered by a customer.
     * @param order the Order instance object.
     * @return the price.
     * @throws SQLException in case the statement could not been created.
     */
    public static double serchForPrice(Order order) throws SQLException {

        double price = 0;

        ResultSet rs = null;
        try {
            Connection myConnection = ConnectionFactory.getConnection();
            Statement stat = myConnection.createStatement();
            rs = stat.executeQuery("SELECT * from product");

            while(rs.next()){

                if(rs.getString(3).equals(order.getProdus())){
                    price = rs.getDouble(4);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;
    }
}
