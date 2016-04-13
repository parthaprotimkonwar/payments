package gateway.bean;

import java.util.Date;

import models.Payments;

public class PaymentsBean {

	public PaymentsBean(Payments payment) {
		this.id = payment.id;
		this.paymentId = payment.paymentId;
		this.dateTime = payment.dateTime;
		this.status = payment.status;
		this.pgReferenceId = payment.pgReferenceId;
	}

	public Long id;
	public String paymentId;
	public Date dateTime;
	public String status;
	public String pgReferenceId;

}
