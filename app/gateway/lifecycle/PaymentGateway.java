package gateway.lifecycle;

import java.util.Map;

import com.frugalbin.common.dto.request.payment.PaymentRequestDto;
import com.frugalbin.common.dto.response.payment.PaymentResponseDto;

public interface PaymentGateway {
	
	public Map<String, String> result(PaymentRequestDto request, String txnid);
	
	public boolean validatePGResponse(PaymentResponseDto request);
	
	public String generateStringHtmlForm(PaymentRequestDto request, Map<String, String> response);
		
}
