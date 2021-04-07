package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {

    @Id
    private String id;

    @Column
    private String addressName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private int NumbersOFRequests;


    @OneToMany(mappedBy = "address")
    private List<Request> request;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }

    public int getNumbersOFRequests() {
        return NumbersOFRequests;
    }

    public void setNumbersOFRequests(int numbersOFRequests) {
        NumbersOFRequests = numbersOFRequests;
    }


}
