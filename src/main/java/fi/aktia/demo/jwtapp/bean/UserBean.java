package fi.aktia.demo.jwtapp.bean;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name= "aktia_user")
public class UserBean {
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator= "aktia_user_seq")
	@SequenceGenerator(name = "aktia_user_seq", sequenceName = "aktia_user_seq", allocationSize = 1)
    private int id;
	@Column(name= "username")
    private String username;
	@Column(name= "password")
    private String password;
	@Column(name= "first_name")
    private String firstName;
	@Column(name= "last_name")
    private String lastName;
	@Column(name= "created_date")
    private Date createdDate;
	
	
	public UserBean() {
		super();
	}
	
	public UserBean(int id, String username, String password, String firstName, String lastName, Date createdDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.createdDate = createdDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
