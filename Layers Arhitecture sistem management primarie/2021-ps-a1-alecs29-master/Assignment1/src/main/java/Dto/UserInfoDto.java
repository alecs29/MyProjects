package Dto;

public class UserInfoDto {

    String name;
    String type;
    String email;
    String addresses;

    public UserInfoDto(String name, String type, String email, String addresses) {
        this.name = name;
        this.type = type;
        this.email = email;
        this.addresses = addresses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }
}
