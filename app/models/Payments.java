package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import utilities.Constants;

@Entity
@Table(name="PAYMENTS", schema=Constants.SCHEMA_NAME_PAYMENT)
public class Payments {

	public Payments() {}
	
	public Payments(Date dateTime, String status, String paymentId) {
		this.dateTime = dateTime;
		this.status = status;
		this.paymentId = paymentId;
	}
	
	@Id
	@Column(name="PAYMENT_ID", length=25, nullable=false)
	public String paymentId;
	
	@Column(name="DATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date dateTime;
	
	@Column(name="STATUS", length=10, nullable=true)
	public String status;
	
	@Column(name="PG_REFERENCE_ID", length=25, nullable=true)
	public String pgReferenceId;
}
