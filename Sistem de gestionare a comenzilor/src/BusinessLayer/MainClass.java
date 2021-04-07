package BusinessLayer;

import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.sql.SQLException;



/**
 * This is the class that contains the main method
 * @author Roman Allexandru
 */
public class MainClass {

    public static void main(String[] args) throws FileNotFoundException {
        try {
            Controller control = new Controller();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
}
