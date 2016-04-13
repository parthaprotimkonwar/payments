package gateway.bean;

import models.PaymentGatewayInfo;

public class PaymentGatewayInfoBean {

	public PaymentGatewayInfoBean() {
	}
	
	public PaymentGatewayInfoBean(PaymentGatewayInfo paymentGatwayInfo) {
		this.pgId = paymentGatwayInfo.pgId;
		this.pgName = paymentGatwayInfo.pgName;
		this.userName = paymentGatwayInfo.userName;
		this.password = paymentGatwayInfo.password;
		this.key = paymentGatwayInfo.key;
		this.salt = paymentGatwayInfo.salt;
		this.token = paymentGatwayInfo.token;
		this.status = paymentGatwayInfo.status;
		this.callbackUrl = paymentGatwayInfo.callbackUrl;
		this.forwardUrl = paymentGatwayInfo.forwardUrl;
		this.successUrl = paymentGatwayInfo.successUrl;
	}
	
	public String pgId;
	public String pgName;
	public String userName;
	public String password;
	public String key;
	public String salt;
	public String token;
	public String status;
	public String successUrl;
	public String callbackUrl;
	public String forwardUrl;
	
}
