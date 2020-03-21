package fi.aktia.demo.jwtapp.bean;

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

@Entity
@Table(name= "aktia_role")
public class RoleBean {
   
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator= "aktia_role_seq")
	@SequenceGenerator(name= "aktia_role_seq", sequenceName = "aktia_role_seq", allocationSize = 1)
	private int id;
	
	@Column(name= "name", length= 50)
	@NotNull
	private String roleName;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name= "aktia_permission",
			joinColumns = {@JoinColumn(name = "fk_role_id", referencedColumnName = "id")},
		    inverseJoinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")}
			)
	private List<PermissionBean> permissions;
	
	public RoleBean() {
		super();
	}

	public RoleBean(int id, @NotNull String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
}