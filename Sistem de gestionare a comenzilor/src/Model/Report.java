package Model;

import java.time.LocalDateTime;
/**
 * Report is a simple class that stores the instance variable of a report.
 * @author Roman Allexandru
 */
public class Report {

    private int reportId = 0;
    private LocalDateTime dateNTime;
    private boolean reportClient = false;
    private boolean reportOrder = false;
    private boolean reportProduct = false;

    /**
     * basic constructor for initialising the instance variables
     * @param id id of the report
     * @param report the name of the report
     */
    public Report(int id, String report) {

        if(report.equals("client"))
            reportClient = true;
        else if(report.equals("order"))
            reportOrder = true;
        else if(report.equals("product"))
            reportProduct = true;

        dateNTime = LocalDateTime.now();
        this.reportId = id;

    }

    /** overriden method for printing the instance variables
     *  @return the String to print
     */
    @Override
    public String toString() {
        return "Report{" +
                "reportClient=" + reportClient +
                ", reportOrder=" + reportOrder +
                ", reportProduct=" + reportProduct +
                '}';
    }


    public int getReportId() {
        return reportId;
    }

    public LocalDateTime getDateNTime() {
        return dateNTime;
    }

    public boolean isReportClient() {
        return reportClient;
    }

    public boolean isReportOrder() {
        return reportOrder;
    }

    public boolean isReportProduct() {
        return reportProduct;
    }

}
