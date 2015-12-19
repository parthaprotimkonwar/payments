package gateway.lifecycle;

import gateway.bean.PaymentGatewayInfoBean;
import gateway.bean.PaymentGatewayResultBean;
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
	public PaymentGatewayResultBean result() {
		PaymentGatewayResultBean resultBean = new PaymentGatewayResultBean(callbackUrl(), paymentGatewayUrl());
		return resultBean;
	}

}
