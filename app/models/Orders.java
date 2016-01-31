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

@Entity
@Table(name="ORDERS")
public class Orders {
	public Orders() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PAYMENT_ID")
	public Long orderId;
	
	@Column(name="DATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date dateTime;
	
	@Column(name="TXN_ID", length = 25, nullable = false) // no need
	public String txnid;
	
	@Column(name = "STATUS", length = 10, nullable = false)
	public String status;
	
	@Column(name = "AMOUNT", length = 30, nullable = false)
	public String amount;
	
	@Column(name = "PRODUCT_INFO", length = 100, nullable = false)  // no need
	public String productinfo;
	
	@Column(name = "FIRST_NAME", length = 60, nullable = false)
	public String firstname;
	
	@Column(name = "LAST_NAME", length = 20, nullable = false)
	public String lastname;
	
	@Column(name = "EMAIL", length = 50, nullable = false)
	public String email;
	
	@Column(name = "PHONE", length = 10, nullable = false)
	public String phone;
	
	@Column(name = "ADDRESS", length = 100, nullable = false) // in auth module
	public String address;
	
	@Column(name = "CITY", length = 50, nullable = false)
	public String city;
	
	@Column(name = "STATE", length = 50, nullable = false)
	public String state;
	
	@Column(name = "COUNTRY", length = 50, nullable = false)
	public String country;
	
	@Column(name = "ZIPCODE", length = 20, nullable = false)
	public String zipcode;
}
