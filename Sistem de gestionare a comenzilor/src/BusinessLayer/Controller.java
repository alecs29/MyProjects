package BusinessLayer;

import DataAccessLayer.ClientDAO;
import DataAccessLayer.OrderDAO;
import DataAccessLayer.ProductDAO;
import DataAccessLayer.ReportDao;
import Model.*;
import Presentation.Bill;
import Presentation.BillValidator;
import Presentation.PdfReport;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * This class controlls the main functions of the OrderManagement aplication.
 * @author Roman Allexandru
 */
public class Controller {

    /** An ArrayList of commands, where commands red from the input file
     * are stored.
     * @see Command
     * @see ArrayList
     */
    private ArrayList<Command> commands;

    /** It counts the client ids. */
    private int clientIds = 0;

    /** It counts the product ids. */
    private int productIds = 0;

    /** It counts the order ids. */
    private int orderIds = 0;

    /** It counts the report ids. */
    private int reportIds = 0;

    /** An instance of the class Presentation.Bill. */
    private Bill bill;


    /**
     * It reads from the input file using the method fileParser
     * and then iterates the ArrayList that stores the commands and make the corresponding
     * operartion(insertion, deletion, etc. in the database).It also makes the necesary bills containing
     * the coresponding orders/Error messajes/table reports.
     *
     * @throws FileNotFoundException  if the input file could not be found.
     *
     * @throws SQLException from the method reportClient in the class  PdfProduct.
     * It is thrown if the ResultSet could not obtain information from the database.
     *
     * @throws DocumentException if a method from PdfProduct coud not open the corresponding document.
     */
    public Controller() throws FileNotFoundException, SQLException, DocumentException {

        commands = new ArrayList<Command>();

        FileReader fr = new FileReader("D:\\faculta\\ANUL 2\\SEMESTRUL2\\TP\\Assignment3\\inText.txt");
        try {
            fileParser(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Command c : commands){

            //---------------------------------------------------INSERT CLIENT
            if(c.isInsertClient()){
                ClientDAO.insertClient((Client)c.getTempCommand());
            }
            //---------------------------------------------------DELETE CLIENT
            else if(c.isDeleteClient()){
                ClientDAO.deleteClient((Client)c.getTempCommand());
            }
            //---------------------------------------------------INSERT PRODUCT
            else if(c.isInsertProduct()){
                ProductDAO.insertProduct((Product)c.getTempCommand());
            }
            //---------------------------------------------------DELETE PRODUCT
            else if(c.isDeleteProduct()){
                ProductDAO.deleteProduct((Product)c.getTempCommand());
            }
            //---------------------------------------------------MAKE ORDER
            else if(c.isMakeOrder()){

                Order order = (Order)c.getTempCommand();

                double pret = BillValidator.serchForPrice(order);

                String mesaj = BillValidator.validate(order);
                if(mesaj == "ok") {
                    OrderDAO.makeOrder((Order) c.getTempCommand());
                    bill = new Bill(order.getNume(), order.getProdus(), order.getCantitate(), pret, "Orderbill");
                }
                else Bill.MsjBill(mesaj, order.getNume());
            }
            //---------------------------------------------------MAKE REPORT
            else if(c.isMakeReport()){

                ReportDao.insertReport((Report)c.getTempCommand());
                Report rep = (Report)c.getTempCommand();

                if(rep.isReportClient())
                    PdfReport.reportClient(rep.getReportId());
                else if(rep.isReportProduct())
                    PdfReport.reportProduct(rep.getReportId());
                else if(rep.isReportOrder())
                    PdfReport.reportOrder(rep.getReportId());


            }

        }
        System.out.println("\n");
        ClientDAO.display();
        System.out.println("\n");
        ProductDAO.display();
        System.out.println("\n");
        OrderDAO.display();
        System.out.println("\n");
        ReportDao.display();



    }

    /**
     *
     * @param fr
     *        The FileReader to read the inputFile characters.
     * @throws IOException
     *         if the method could not read from input file.
     */

    private void fileParser(FileReader fr) throws IOException {


        int c = '1';
        int cop = '1';
        String buffer = "";

        c = fr.read();
        while( !( (c >= 'A' && c <='Z')  || (c >= 'a' && c <='z') ) ){ //citim spatii libere din prima linie daca sunt
            c = fr.read();

        }
        buffer = buffer + (char)c;

        int i = 1;
        cop = c;
        String aux = "";
        boolean ok = true;
        //----------------------------------------------------------------citire
        while( ( c = fr.read() ) != -1 ){

            if(c != 13 && c != 10)
                buffer = buffer + (char) c;

              if(c == 10  &&  cop == 13) { //------------------------------------sfarsit de linie

                  formatare(buffer);
                  buffer = "";
              }
              i++;
              cop = c;

        }

        formatare(buffer);
    }

    /**
     * @param buffer
     *        The string to be verifyed and matched with different regex expressions to
     *        be cataloged as a specific coommand.
     */
    public void formatare(String buffer){
        System.out.println(buffer);

        //**************************************************************  CAZ INSERT CLIENT
        if (buffer.matches("(.*)Insert client(.*)")) {

            int index = buffer.indexOf(':');
            String substring = buffer.substring(index + 1);
            substring = substring.trim();

            String[] temp = substring.split(",\\s*");
            int j = 0;

            commands.add(new Command(new Client(++clientIds, temp[0], temp[1]), 1));

        }

        //**************************************************************  CAZ DELETE CLIENT
        else if (buffer.matches("(.*)Delete client(.*)")) {

            int index = buffer.indexOf(':');
            String substring = buffer.substring(index + 1);
            substring = substring.trim();

            String[] temp = substring.split(",\\s*");
            int j = 0;
            boolean ok = false;
            for(Command c : commands){

                if(c.isInsertClient()){

                    Client client = (Client)c.getTempCommand();
                    if(temp[0].equals(client.getNume())  &&   temp[1].equals(client.getOras())){
                        ok = true;
                        commands.add(new Command(new Client(client.getId(), temp[0], temp[1]),2));
                        break;
                    }
                }
            }
            if(ok == false){
                System.out.println("Clientul " + temp[0] + ", " + temp[1] + " nu poate fii sters pentru ca nu exista" );
            }


        }

        //**************************************************************  CAZ INSERT PRODUS
        else if (buffer.matches("(.*)Insert product(.*)")) {

            int index = buffer.indexOf(':');
            String substring = buffer.substring(index + 1);
            substring = substring.trim();

            String[] temp = substring.split(",\\s*");
            int j = 0;

            int cantitate = Integer.parseInt(temp[1]);
            double pret = Double.parseDouble(temp[2]);

            commands.add(new Command(new Product(++productIds, temp[0], cantitate, pret),3));
        }

        //**************************************************************  CAZ DELETE PRODUCT
        else if (buffer.matches("(.*)Delete [Pp]roduct(.*)")) {

            int index = buffer.indexOf(':');
            String substring = buffer.substring(index + 1);
            substring = substring.trim();

            boolean ok = false;
            for(Command c : commands){

                if(c.isInsertProduct()){

                    Product product = (Product)c.getTempCommand();
                    if(substring.equals(product.getDenumire())){
                        ok = true;
                        commands.add(new Command(new Product(product.getId(), product.getDenumire(), product.getCantitate(), product.getPret()),4));
                        break;
                    }
                }
            }
            if(ok == false){
                System.out.println("Prodsul " + substring + " nu poate fii sters pentru ca nu exista" );
            }

            //commands.add(new Command(new Product(-1, substring, 0, 0),4));
        }

        //**************************************************************  CAZ MAKE ORDER
        else if (buffer.matches("(\\s*)[Oo]rder(.*)")) {

            int index = buffer.indexOf(':');
            String substring = buffer.substring(index + 1);
            substring = substring.trim();

            String[] temp = substring.split(",\\s*");
            int j = 0;


            int pret = Integer.parseInt(temp[2]);

            commands.add(new Command(new Order(++orderIds, temp[0], temp[1], pret),5));
        }

        //**************************************************************  CAZ MAKE REPORT
        else if (buffer.matches("Report(.*)")) {

            buffer = buffer.trim();

            String[] temp = buffer.split("\\s");
            int j = 0;

            if(temp[1].equals("client"))
                commands.add(new Command(new Report(++reportIds,"client"),6));
            else if(temp[1].equals("product"))
                commands.add(new Command(new Report(++reportIds,"product"),6));
            else if(temp[1].equals("order"))
                commands.add(new Command(new Report(++reportIds,"order"),6));
        }
    }


}
