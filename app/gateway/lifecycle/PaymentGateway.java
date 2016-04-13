package gateway.lifecycle;

import java.util.Map;

import controllers.requestdto.PaymentRequestDto;
import controllers.responsedto.PaymentResponseDto;

public interface PaymentGateway {
	
	public Map<String, String> result(PaymentRequestDto request, String txnid);
	
	public boolean validatePGResponse(PaymentResponseDto request);
	
	public String generateStringHtmlForm(PaymentRequestDto request, Map<String, String> response);
		
}
