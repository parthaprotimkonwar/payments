package gateway.lifecycle;

public interface PaymentGateway {

	public String paymentGatewayUrl();
	
	public String callbackUrl();
	
	public Boolean initialized();
	
}
