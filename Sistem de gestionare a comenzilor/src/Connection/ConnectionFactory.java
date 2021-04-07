package Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used for creating the connection between java and the database.
 * @author Roman Allexandru
 */
public class ConnectionFactory {

    /** creating a logger for a named subsystem*/
    private static final Logger logger = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String dburl = "jdbc:mysql://localhost:3307/warehouse?useSSL=false&useTimezone=true&serverTimezone=UTC";//?useTimezone=true&serverTimezone=UTC
    private static final String user = "root";
    private static final String pass = "Redbullbcone123!";

    /** the single instance for the ConnectionFactory*/
    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * The constructor loads the object associated with the class jdbc.Driver.
     */
    private ConnectionFactory(){
        try {

            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** It creates the connection with the database.
     * @return the connection
     */
    private Connection createConnection(){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dburl,user,pass);

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Conectarea la baza de date nu s-a putut realiza");
            e.printStackTrace();
        }

        return connection;
    }


    public static Connection getConnection(){
        return singleInstance.createConnection();
    }


}
