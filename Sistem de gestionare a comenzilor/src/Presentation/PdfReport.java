package Presentation;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Connection.ConnectionFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class creates a .pdf file that contains a table for s specific report
 */
public class PdfReport {

    public static int reportClientCount = 0;
    public static int reportProductCount = 0;
    public static int reportOrderCount = 0;

    public static int id = 1;

    public static String locationRC = "D:\\faculta\\ANUL 2\\SEMESTRUL2\\TP\\Assignment3\\";
    public static String locationRP = "D:\\faculta\\ANUL 2\\SEMESTRUL2\\TP\\Assignment3\\";
    public static String locationRO = "D:\\faculta\\ANUL 2\\SEMESTRUL2\\TP\\Assignment3\\";

    /**
     * Constructor for deciding which type of report it will be made
     * @param type a number that triggeres a specific report. (1, 2, 3) are (reportClient, reportProduct, reportOrder)
     * @param id is the report id in the database
     * @throws DocumentException if the method coud not open the corresponding document.
     * @throws SQLException if an error occured in getting the information from the database.
     */
    public PdfReport(int type,int id) throws DocumentException, SQLException {

        if(type == 1)
            reportClient(id);
        else if(type == 2)
            reportProduct(id);
        else if(type == 3)
            reportOrder(id);
    }

    /**
     * creates a .pdf file containing the report of the Client table from the database using Itext5 library.
     * @param id is the report id in the database
     * @throws DocumentException if the method coud not open the corresponding document.
     * @throws SQLException if an error occured in getting the information from the database.
     */
    public static void reportClient(int id) throws DocumentException, SQLException {

        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(locationRC + "reportClient"+ (++reportClientCount) + ".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //-------------------------------------------starting pdf
        writer.setPdfVersion(PdfWriter.VERSION_1_7);
        writer.setTagged();

        writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
        document.addLanguage("en-US");
        document.addTitle("English pangram");
        writer.createXmpMetadata();
        //----------------------------------------------
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED, 20);

        Paragraph p = new Paragraph("Report client", font);
        document.add(p);
        p = new Paragraph("\n", font);
        document.add(p);

        PdfPTable table = new PdfPTable(4);
        ResultSet myrs = getTableResultSet(1);

        table.addCell("id");
        table.addCell("name");
        table.addCell("city");
        table.addCell("deleted");


        while(myrs.next()){
            table.addCell(String.valueOf(myrs.getInt(1)));
            table.addCell(String.valueOf(myrs.getString(2)));
            table.addCell(String.valueOf(myrs.getString(3)));
            table.addCell(String.valueOf(myrs.getInt(4)));
        }
        document.add(table);

        p = new Paragraph("\n", font);
        document.add(p);

        String dateAndTime = "";

        ResultSet rs = null;
        try {
            Connection myConnection = ConnectionFactory.getConnection();
            Statement stat = myConnection.createStatement();
            rs = stat.executeQuery("SELECT * from report");

            while (rs.next()){
                if(rs.getInt(1) == id) {
                    dateAndTime = rs.getString(2);
                    font = FontFactory.getFont(FontFactory.COURIER_OBLIQUE, BaseFont.WINANSI, BaseFont.EMBEDDED, 13);
                    p = new Paragraph(dateAndTime, font);
                    document.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        document.close();
    }

    /**
     * creates a ResultSet using the query "SELECT * from tableName".
     * @param tableNr is used for choosing the right table. (1, 2, 3) are (client, product, orders).
     * @return the ResultSet object.
     */
    public static ResultSet getTableResultSet(int tableNr){

        ResultSet rs = null;
        try {
            Connection myConnection = ConnectionFactory.getConnection();
            Statement stat = myConnection.createStatement();
            if(tableNr == 1)
                rs = stat.executeQuery("SELECT * from client");
            else if(tableNr == 2)
                rs = stat.executeQuery("SELECT * from product");
            else if(tableNr == 3)
                rs = stat.executeQuery("SELECT * from orders");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * creates a .pdf file containing the report of the Product table from the database using Itext5 library.
     * @param id is the report id in the database
     * @throws DocumentException if the method coud not open the corresponding document.
     * @throws SQLException if an error occured in getting the information from the database.
     */
    public static void reportProduct(int id) throws DocumentException, SQLException{

        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(locationRC + "reportProduct" + (++reportProductCount) + ".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //-------------------------------------------starting pdf
        writer.setPdfVersion(PdfWriter.VERSION_1_7);
        writer.setTagged();

        writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
        document.addLanguage("en-US");
        document.addTitle("English pangram");
        writer.createXmpMetadata();
        //----------------------------------------------
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED, 20);

        Paragraph p = new Paragraph("Report product", font);
        document.add(p);
        p = new Paragraph("\n", font);
        document.add(p);

        PdfPTable table = new PdfPTable(5);
        ResultSet myrs = getTableResultSet(2);

        table.addCell("id");
        table.addCell("quantity");
        table.addCell("productName");
        table.addCell("price");
        table.addCell("deleted");


        while(myrs.next()){
            table.addCell(String.valueOf(myrs.getInt(1)));
            table.addCell(String.valueOf(myrs.getString(2)));
            table.addCell(String.valueOf(myrs.getString(3)));
            table.addCell(String.valueOf(myrs.getDouble(4)));
            table.addCell(String.valueOf(myrs.getDouble(5)));
        }

        document.add(table);
        document.close();

    }


    /**
     * creates a .pdf file containing the report of the Order table from the database using Itext5 library.
     * @param id is the report id in the database
     * @throws DocumentException if the method coud not open the corresponding document.
     * @throws SQLException if an error occured in getting the information from the database.
     *
     */
    public static void reportOrder(int id) throws DocumentException, SQLException{


        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(locationRC + "reportOrder" + (++reportOrderCount) + ".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //-------------------------------------------starting pdf
        writer.setPdfVersion(PdfWriter.VERSION_1_7);
        writer.setTagged();

        writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
        document.addLanguage("en-US");
        document.addTitle("English pangram");
        writer.createXmpMetadata();
        //----------------------------------------------
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED, 20);

        Paragraph p = new Paragraph("Report order", font);
        document.add(p);
        p = new Paragraph("\n", font);
        document.add(p);

        PdfPTable table = new PdfPTable(4);
        ResultSet myrs = getTableResultSet(3);

        table.addCell("orderId");
        table.addCell("orderName");
        table.addCell("orderProduct");
        table.addCell("orderQuantity");


        while(myrs.next()){
            table.addCell(String.valueOf(myrs.getInt(1)));
            table.addCell(String.valueOf(myrs.getString(2)));
            table.addCell(String.valueOf(myrs.getString(3)));
            table.addCell(String.valueOf(myrs.getInt(4)));
        }

        document.add(table);
        document.close();

    }


}
