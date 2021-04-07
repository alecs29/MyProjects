package Dto;

public class RequestDto {


    private String documentName;
    private String user;
    private String address;
    private String approved;

    public RequestDto(String documentName, String user, String address, String approved) {
        this.documentName = documentName;
        this.user = user;
        this.address = address;
        this.approved = approved;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }
}
