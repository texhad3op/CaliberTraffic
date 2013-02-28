package net.texhad3op.ejb.entities;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import net.texhad3op.ejb.base.BaseEntity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "Calibers")
@SqlResultSetMappings({
//		@SqlResultSetMapping(name = "CalibersWithWorkers", columns = { @ColumnResult(name = "operationtype"),
//				@ColumnResult(name = "userlogin"), @ColumnResult(name = "id"), @ColumnResult(name = "istaken"),
//				@ColumnResult(name = "caliberId"), @ColumnResult(name = "deviceNr"),
//				@ColumnResult(name = "measurementNominal"), @ColumnResult(name = "measurementTolerance"),
//				@ColumnResult(name = "minCaliber"), @ColumnResult(name = "maxCaliber"), @ColumnResult(name = "holdPlace"),
//				@ColumnResult(name = "holdSubPlace"),@ColumnResult(name = "caliberordertype"),@ColumnResult(name = "isActive")
//				,@ColumnResult(name = "workerid")
//
//		}),

		@SqlResultSetMapping(name = "CalibersTraffic", columns = { @ColumnResult(name = "deviceNr"), @ColumnResult(name = "operationtype"),
				@ColumnResult(name = "registered"), @ColumnResult(name = "id"), @ColumnResult(name = "firstname") }),

		@SqlResultSetMapping(name = "CalibersForChecking", columns = { @ColumnResult(name = "caliberId"),
				@ColumnResult(name = "nextCheckingTime"), @ColumnResult(name = "deviceNr") })

})
public class Caliber extends BaseEntity {

	@Id
	@GeneratedValue(generator = "calibersSeqName")
	@GenericGenerator(name = "calibersSeqName", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "calibers_id_seq") })
	private Long id;

	@Column(name = "measurementNominal", nullable = true)
	Float measurementNominal;

	@Column(name = "measurementTolerance", nullable = true)
	String measurementTolerance;

	@Column(name = "minCaliber", nullable = true)
	Float minCaliber;

	@Column(name = "maxCaliber", nullable = true)
	Float maxCaliber;

	@Column(name = "holdPlace", nullable = true)
	String holdPlace;

	@Column(name = "description", nullable = true)
	String description;	
	
	@Column(name = "holdSubPlace", nullable = true)
	String holdSubPlace;

	@Column(name = "deviceNr", nullable = false)
	String deviceNr;

	@Column(name = "isLongType", nullable = false)
	Boolean isLongType;

	@Column(name = "isTaken", nullable = false)
	Boolean isTaken;

	@Column(name = "isActive", nullable = false)
	Boolean isActive;

	@Column(name = "registered")
	private Timestamp registered;

	@Column(name = "lastCaliberOperationId", nullable=true)	
	Long lastCaliberOperationId;

	@Column(name = "lastCaliberOrderId", nullable=true)	
	Long lastCaliberOrderId;	
	
	@Column(name = "nextCheckingTime")
	private Timestamp nextCheckingTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getMeasurementNominal() {
		return measurementNominal;
	}

	public void setMeasurementNominal(Float measurementNominal) {
		this.measurementNominal = measurementNominal;
	}

	public String getMeasurementTolerance() {
		return measurementTolerance;
	}

	public void setMeasurementTolerance(String measurementTolerance) {
		this.measurementTolerance = measurementTolerance;
	}

	public Float getMinCaliber() {
		return minCaliber;
	}

	public void setMinCaliber(Float minCaliber) {
		this.minCaliber = minCaliber;
	}

	public Float getMaxCaliber() {
		return maxCaliber;
	}

	public void setMaxCaliber(Float maxCaliber) {
		this.maxCaliber = maxCaliber;
	}

	public String getHoldPlace() {
		return holdPlace;
	}

	public void setHoldPlace(String holdPlace) {
		this.holdPlace = holdPlace;
	}

	public String getHoldSubPlace() {
		return holdSubPlace;
	}

	public void setHoldSubPlace(String holdSubPlace) {
		this.holdSubPlace = holdSubPlace;
	}

	public String getDeviceNr() {
		return deviceNr;
	}

	public void setDeviceNr(String deviceNr) {
		this.deviceNr = deviceNr;
	}

	public Boolean getIsLongType() {
		return isLongType;
	}

	public void setIsLongType(Boolean isLongType) {
		this.isLongType = isLongType;
	}

	public Boolean getIsTaken() {
		return isTaken;
	}

	public void setIsTaken(Boolean isTaken) {
		this.isTaken = isTaken;
	}

	public Timestamp getRegistered() {
		return registered;
	}

	public void setRegistered(Timestamp registered) {
		this.registered = registered;
	}

	public Timestamp getNextCheckingTime() {
		return nextCheckingTime;
	}

	public void setNextCheckingTime(Timestamp nextCheckingTime) {
		this.nextCheckingTime = nextCheckingTime;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getLastCaliberOperationId() {
		return lastCaliberOperationId;
	}

	public void setLastCaliberOperationId(Long lastCaliberOperationId) {
		this.lastCaliberOperationId = lastCaliberOperationId;
	}

	public Long getLastCaliberOrderId() {
		return lastCaliberOrderId;
	}

	public void setLastCaliberOrderId(Long lastCaliberOrderId) {
		this.lastCaliberOrderId = lastCaliberOrderId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
