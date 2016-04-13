package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import utilities.Constants;

@Entity
@Table(name="PAYMENT_GATEWAY_INFO", schema=Constants.SCHEMA_NAME_PAYMENT)
public class PaymentGatewayInfo {

	public PaymentGatewayInfo() {
	}
	
	public PaymentGatewayInfo(String pgId, String pgName, String userName, String password, String key, String salt, String token,  String status, String successUrl, String callbackUrl, String forwardUrl) {
		this.pgId = pgId;
		this.pgName = pgName;
		this.userName = userName;
		this.password = password;
		this.key = key;
		this.salt = salt;
		this.token = token;
		this.status = status;
		this.successUrl = successUrl;
		this.callbackUrl = callbackUrl;
		this.forwardUrl = forwardUrl;
	}
	
	@Id
	@Column(name="PG_ID", length=20, nullable=false)
	public String pgId;
	
	@Column(name="PG_NAME", length=25, nullable=false)
	public String pgName;
	
	@Column(name="USERNAME", length=20, nullable=true)
	public String userName;
	
	@Column(name="PASSWORD", length=40, nullable=true)
	public String password;
	
	@Column(name="key", length=20, nullable=true)
	public String key;
	
	@Column(name="salt", length=25, nullable=true)
	public String salt;
	
	@Column(name="TOKEN", length=100, nullable=true)
	public String token;
	
	@Column(name="STATUS", length=10, nullable=false)
	public String status;
	
	@Column(name="SUCCESS_URL", length=100, nullable=false)
	public String successUrl;
	
	@Column(name="CALLBACK_URL", length=100, nullable=false)
	public String callbackUrl;
	
	@Column(name="FORWARD_URL", length=100, nullable=false)
	public String forwardUrl;
	
}
