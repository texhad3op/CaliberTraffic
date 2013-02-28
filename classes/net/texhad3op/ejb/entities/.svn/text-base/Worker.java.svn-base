package net.texhad3op.ejb.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import net.texhad3op.ejb.Utils;
import net.texhad3op.ejb.base.BaseEntity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name = "workers")
public class Worker extends BaseEntity{

	@Id
	@GeneratedValue(generator = "workersSeqName")
	@GenericGenerator(name = "workersSeqName", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "workers_id_seq") })
	private Long id;

	@Column(name = "firstName", length = 250, nullable = true)
	private String firstName;
	
	@Column(name = "lastName", length = 250, nullable = true)
	private String lastName;	

	@Column(name = "description", length = 2000, nullable = true)
	private String description;

	@Column(name = "userLogin", length = 50, nullable = false)
	private String userLogin;

	@Column(name = "userPassword", length = 50, nullable = false)
	private String userPassword;
	
    @Column(name = "type")     
    private Integer type = 0;	
	
    @Column(name = "isBlocked")     
    private Boolean isBlocked;	    
    
	@Column(name = "registered")
	private Timestamp registered;    
    
	public WorkerType getType() {
		return Utils.getWorkerType(type);
	}

	public void setType(WorkerType workerType) {
		this.type = workerType.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Timestamp getRegistered() {
		return registered;
	}

	public void setRegistered(Timestamp registered) {
		this.registered = registered;
	}

	public Boolean getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	
}
