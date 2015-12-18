package gateway.lifecycle;

import java.util.HashMap;
import java.util.Map;

import utilities.Constants;

public class PaymentGatewayManager {

	Map<String, PaymentGateway> cachedPaymentGateways = new HashMap<>();
	Object lock = new Object();
	
	private static PaymentGatewayManager manager = new PaymentGatewayManager();
	
	private PaymentGatewayManager() {}
	
	public static PaymentGatewayManager getInstance() {
		return manager;
	}
	
	/**
	 * Method to get the Payment Gateway instance
	 * @param pgId
	 * @return
	 */
	public PaymentGateway paymentGatewayInstance(String pgId) {
		
		if(pgId == null)
			return null;
		
		PaymentGateway paymentGateway = null;
		if(cachedPaymentGateways.containsKey(pgId) && cachedPaymentGateways.get(pgId) != null) {
			return cachedPaymentGateways.get(pgId);
		} else {
			synchronized (lock) {
				if(pgId.equals(Constants.PG_ID_PAYU)) {
					paymentGateway = new PayUPaymentGateway();
				} else {
					paymentGateway = new PayUPaymentGateway();
				}
				if(!cachedPaymentGateways.containsKey(pgId)) {
					cachedPaymentGateways.put(pgId, paymentGateway);
				}
			}
		}
		return cachedPaymentGateways.get(pgId);
	}
}
