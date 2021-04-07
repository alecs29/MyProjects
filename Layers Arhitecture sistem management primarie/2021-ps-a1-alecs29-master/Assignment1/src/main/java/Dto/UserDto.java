package Dto;

import entity.Address;
import entity.Request;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

public class UserDto {


    private String id;
    private String name;
    private String password;
    private String admin_user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdmin_user() {
        return admin_user;
    }

    public void setAdmin_user(String admin_user) {
        this.admin_user = admin_user;
    }
}
