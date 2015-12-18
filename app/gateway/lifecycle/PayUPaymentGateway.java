package gateway.lifecycle;

import gateway.cache.PaymentGatewayCache;
import gateway.cache.PaymentGatewayInfoBean;
import utilities.Constants;

public class PayUPaymentGateway implements PaymentGateway{

	private static PaymentGatewayInfoBean pgInfo = PaymentGatewayCache.getInstance().paymentGatewayInfo(Constants.PG_ID_PAYU);
	
	@Override
	public String paymentGatewayUrl() {
		return pgInfo.pgUrl;
	}

	@Override
	public String callbackUrl() {
		return pgInfo.callbackUrl;
	}

	@Override
	public Boolean initialized() {
		// TODO Auto-generated method stub
		return null;
	}

}
