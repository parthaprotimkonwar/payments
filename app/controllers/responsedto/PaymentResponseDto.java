package controllers.responsedto;

public class PaymentResponseDto {

	public String token;
	public String paymentId;
	
	public PaymentResponseDto(String token, String paymentId) {
		this.token = token;
		this.paymentId = paymentId;
	}
}
