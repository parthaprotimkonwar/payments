package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import utilities.Constants;

@Entity
@Table(name="PAYMENT_GATEWAY_INFO", schema=Constants.SCHEMA_NAME_PAYMENT)
public class PaymentGatewayInfo {

	@Id
	@Column(name="PG_ID", length=20, nullable=false)
	public String pgId;
	
	@Column(name="PG_NAME", length=25, nullable=false)
	public String pgName;
	
	@Column(name="PG_URL", length=255, nullable=false)
	public String pgUrl;
	
	@Column(name="USERNAME", length=20, nullable=true)
	public String userName;
	
	@Column(name="PASSWORD", length=40, nullable=true)
	public String password;
	
	@Column(name="TOKEN", length=100, nullable=true)
	public String token;
	
	@Column(name="STATUS", length=10, nullable=false)
	public String status;
	
	@Column(name="CALLBACK_URL", length=255, nullable=false)
	public String callbackUrl;
	
}
