package controllers.responsedto;

public class PaymentResponseDto {

	public String token;
	public Long paymentId;
	
	public PaymentResponseDto(String token, Long paymentId) {
		this.token = token;
		this.paymentId = paymentId;
	}
}
