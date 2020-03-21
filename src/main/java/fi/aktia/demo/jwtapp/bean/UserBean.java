package fi.aktia.demo.jwtapp.bean;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name= "aktia_user")
public class UserBean {
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator= "aktia_user_seq")
	@SequenceGenerator(name = "aktia_user_seq", sequenceName = "aktia_user_seq", allocationSize = 1)
    private int id;
	
	@Column(name= "username", length= 50, unique = true)
	@NotNull
	@Size(min= 4, max= 50)
    private String username;
	
	@Column(name= "password", length= 50)
	@NotNull
	@Size(min= 6, max= 50)
    private String password;
	
	@Column(name= "first_name", length= 50)
	@Size(min= 1, max= 50)
	private String firstName;
	
	@Column(name= "last_name", length= 50)
	@Size(min= 1, max= 50)
    private String lastName;
	
	@Column(name= "created_date")
    private Date createdDate;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name= "aktia_permission",
			joinColumns = {@JoinColumn(name = "fk_user_id", referencedColumnName = "id")},
		    inverseJoinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")}
			)
	private List<PermissionBean> permissions;
	
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
