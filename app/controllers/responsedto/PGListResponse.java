package controllers.responsedto;

import java.util.List;

import gateway.bean.PaymentGatewayInfoBean;

public class PGListResponse {

	public PGListResponse(List<PaymentGatewayInfoBean> paymentGateways) {
		this.paymentGateways = paymentGateways;
	}
	
	public List<PaymentGatewayInfoBean> paymentGateways;
	
}
