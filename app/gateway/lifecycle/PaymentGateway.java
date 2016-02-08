package gateway.lifecycle;

import java.util.Map;

public interface PaymentGateway {
	public Map<String, String> result(Map<String, String> request, String txnid);
}
