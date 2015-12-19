package gateway.bean;

import models.PaymentGatewayInfo;

public class PaymentGatewayInfoBean {

	public PaymentGatewayInfoBean() {
	}
	
	public PaymentGatewayInfoBean(PaymentGatewayInfo paymentGatwayInfo) {
		this.pgId = paymentGatwayInfo.pgId;
		this.pgName = paymentGatwayInfo.pgName;
		this.pgUrl = paymentGatwayInfo.pgUrl;
		this.userName = paymentGatwayInfo.userName;
		this.password = paymentGatwayInfo.password;
		this.token = paymentGatwayInfo.token;
		this.status = paymentGatwayInfo.status;
		this.callbackUrl = paymentGatwayInfo.callbackUrl;
	}
	
	public String pgId;
	public String pgName;
	public String pgUrl;
	public String userName;
	public String password;
	public String token;
	public String status;
	public String callbackUrl;
	
}
