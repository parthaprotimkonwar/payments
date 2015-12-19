package gateway.lifecycle;

import gateway.bean.PaymentGatewayResultBean;

public interface PaymentGateway {

	public String paymentGatewayUrl();
	
	public String callbackUrl();
	
	public PaymentGatewayResultBean result();
}
