package controllers.responsedto;

import java.util.List;

import gateway.bean.PaymentsBean;

public class PaymentListResponse {

	public String token;
	public List<PaymentsBean> paymentsList;

	public PaymentListResponse(String token, List<PaymentsBean> paymentsList) {
		this.token = token;
		this.paymentsList = paymentsList;
	}
}
