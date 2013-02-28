package net.texhad3op.ejb.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import net.texhad3op.ejb.Utils;
import net.texhad3op.ejb.base.BaseEntity;

@Entity
@Table(name = "CaliberOrders")
public class CaliberOrder extends BaseEntity {
	@Id
	@GeneratedValue(generator = "caliberOrdersSeqName")
	@GenericGenerator(name = "caliberOrdersSeqName", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "caliberorders_id_seq") })
	private Long id;
	
	@Column(name = "registered")
	private Timestamp registered;	
	
	@ManyToOne
	@JoinColumn(name = "caliberId")
	private Caliber caliber;
	
	@ManyToOne
	@JoinColumn(name = "workerId")
	private Worker worker;

	@Column(name = "isActive")
	private Boolean isActive;		
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getRegistered() {
		return registered;
	}

	public void setRegistered(Timestamp registered) {
		this.registered = registered;
	}

	public Caliber getCaliber() {
		return caliber;
	}

	public void setCaliber(Caliber caliber) {
		this.caliber = caliber;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}	
	
	@Column(name = "caliberOrderType")
	private Integer caliberOrderType = 0;

	public CaliberOrderType getCaliberOrderType() {
		return Utils.getCaliberOrderType(caliberOrderType);
	}

	public void setCaliberOrderType(CaliberOrderType caliberOrderType) {
		this.caliberOrderType = caliberOrderType.getId();
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	
}
