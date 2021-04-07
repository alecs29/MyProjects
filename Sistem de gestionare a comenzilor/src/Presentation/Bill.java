package Presentation;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;


/**
 * This class generates the bill for an order and stores it in a specific location
 */
public class Bill {

    /** Static variable used for generating the name of the .pdf file*/
    private static int billCount = 0;


    public static String location = "D:\\faculta\\ANUL 2\\SEMESTRUL2\\TP\\Assignment3\\";

    /**
     * Constructor for initialising the makeOrderPdf method with the coppesponding aruments.
     * It constructs the name of the location.
     * @param name the name of the person who made the  order.
     * @param product the name of the product ordered.
     * @param quantity the number quantity ordered.
     * @param price the price of the whole order.
     * @param file the partial string location the .pdf file will be stored.
     */
    public Bill(String name, String product, int quantity, Double price, String file) {

        try {

            makeOrderPdf(location + file + (++billCount) + ".pdf", name, product, quantity, price);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    /**
     * Using the given arguments this method creates a bill in .pdf format for an order made by a client.
     * @param dest the location the .pdf file will be stored.
     * @param name the name of the person who made the  order.
     * @param product the name of the product ordered.
     * @param quantity the number quantity ordered.
     * @param price the price of the whole order.
     * @throws DocumentException if an error occurs when adding an element.
     */
    public static void makeOrderPdf(String dest, String name, String product, int quantity, Double price) throws DocumentException {

        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
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
        Paragraph p = new Paragraph("                                                                                            --Order bill--", font);
        Paragraph p1 = new Paragraph();
        Paragraph p2 = new Paragraph();
        Paragraph p3 = new Paragraph();
        Paragraph p4 = new Paragraph();


        double total = price * quantity;
        font = FontFactory.getFont(FontFactory.COURIER_OBLIQUE, BaseFont.WINANSI, BaseFont.EMBEDDED, 14);


        p1 = new Paragraph("Product -------------- " + product, font);
        p2 = new Paragraph("Quantity ------------- " + quantity, font);
        p3 = new Paragraph("Price ---------------- " + product, font);
        p4 = new Paragraph("=============================   Total : " + total + " ron", font);


        document.add(p);
        document.add(p1);
        document.add(p2);
        document.add(p3);
        document.add(p4);


        LocalDateTime ldt = LocalDateTime.now();

        p = new Paragraph("\n\n", font);

        document.add(p);

        font = FontFactory.getFont(FontFactory.COURIER_OBLIQUE, BaseFont.WINANSI, BaseFont.EMBEDDED, 12);
        p = new Paragraph("Order mde by customer: " + name, font);
        document.add(p);
        p = new Paragraph("date: " + ldt.getDayOfMonth() + " " + ldt.getMonth() + " " + ldt.getYear(), font);
        document.add(p);


        document.close();
    }



    /**
     * Using the given message this method creates a bill in .pdf format for an order made by a client.
     * @param mesaj  the customer's name.
     * @param name   the name of the message.
     * @throws DocumentException if an error occurs when adding an element.
     */
    public static void MsjBill(String mesaj, String name) throws DocumentException {


        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(location + (++billCount) + ".pdf"));
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

        Paragraph p = new Paragraph(mesaj, font);
        document.add(p);


        LocalDateTime ldt = LocalDateTime.now();

        p = new Paragraph("\n\n", font);
        document.add(p);

        font = FontFactory.getFont(FontFactory.COURIER_OBLIQUE, BaseFont.WINANSI, BaseFont.EMBEDDED, 12);
        p = new Paragraph("Order mde by customer: " + name, font);
        document.add(p);
        p = new Paragraph("date: " + ldt.getDayOfMonth() + " " + ldt.getMonth() + " " + ldt.getYear(), font);
        document.add(p);
        document.close();

    }
}
