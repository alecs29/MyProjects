package DataAccessLayer;

import Model.Order;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.logging.Logger;
import Connection.ConnectionFactory;
import Model.Report;

/**
 * This class is responsable for making the necessary operation to the Report database(insert,display).
 * @author Roman Allexandru
 */
public class ReportDao {

    protected static final Logger logger = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO report(idReport, date) values (?,?)";

    /**
     * Basic constructor with no paramaters
     */
    public ReportDao(){
    }

    /**
     * This method is used for inserting a report in the Report table from the database.
     * @param report the order to be inserted in the database.
     */
    public static void insertReport(Report report){

        Connection myConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        ResultSet rs = null;

        try {
            insertStatement = myConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, report.getReportId());
            LocalDateTime time = report.getDateNTime();
            insertStatement.setString(2, "Data: " + time.getDayOfMonth() + "-" + time.getMonth() + "-" + time.getYear() + " " + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());

            int rowsAffected = insertStatement.executeUpdate();

            rs = insertStatement.getGeneratedKeys();

            System.out.println("(" + rowsAffected + ") rows affected");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method used for displaying the Report table in the console.
     */
    public static void display(){

        try {
            Connection myConnection = ConnectionFactory.getConnection();
            Statement stat = myConnection.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * from report");

            System.out.println("REPORT");
            while(rs.next()){

                System.out.println(rs.getInt(1) + ", " + rs.getString(2) );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();

    }

}
