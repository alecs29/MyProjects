package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	private String id;
	
	@Column
	private String name;

	@Column
	private String password;

	@Column
	private String email;

	@Column
	private String admin_user;

	@OneToMany(mappedBy = "user")
    private List<Address> addresses;

	@OneToMany(mappedBy = "user")
	private List<Document> documents;

	@OneToMany(mappedBy = "user")
	private List<Request> requests;

	public User(String name) {
		this.name = name;
	}

	public User() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
