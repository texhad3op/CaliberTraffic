package net.texhad3op.ejb.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import net.texhad3op.ejb.Utils;
import net.texhad3op.ejb.base.BaseEntity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CaliberOperations")
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "OperationsWithWorkers", columns = { @ColumnResult(name = "operationtype"),
				@ColumnResult(name = "userlogin"), @ColumnResult(name = "id"), @ColumnResult(name = "deviceNr"),
				@ColumnResult(name = "istaken"), @ColumnResult(name = "caliberId") }),

		@SqlResultSetMapping(name = "OperationsByWorker", columns = { @ColumnResult(name = "operationtype"),
				@ColumnResult(name = "registered"), @ColumnResult(name = "deviceNr") }) })
public class CaliberOperation extends BaseEntity {

	@Id
	@GeneratedValue(generator = "caliberOperationsSeqName")
	@GenericGenerator(name = "caliberOperationsSeqName", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "caliberOperations_id_seq") })
	private Long id;

	@ManyToOne
	@JoinColumn(name = "caliberId")
	private Caliber caliber;

	@ManyToOne
	@JoinColumn(name = "workerId")
	private Worker worker;

	@Column(name = "registered")
	private Timestamp registered;

	public Timestamp getRegistered() {
		return registered;
	}

	public void setRegistered(Timestamp registered) {
		this.registered = registered;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Column(name = "operationType")
	private Integer operationType = 0;

	public OperationType getType() {
		return Utils.getOperationType(operationType);
	}

	public void setType(OperationType operationType) {
		this.operationType = operationType.getId();
	}
}
