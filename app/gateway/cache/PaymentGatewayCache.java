package gateway.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.PaymentGatewayInfo;

/**
 * Singleton Class to get the PaymentGateway Initialized during startup.
 * @author pkonwar
 */
public class PaymentGatewayCache {

	private PaymentGatewayCache() {}
	
	private static PaymentGatewayCache paymentGatewayCache = new PaymentGatewayCache();
	
	public static PaymentGatewayCache getInstance() {
		return paymentGatewayCache;
	}
	
	private static Map<String, PaymentGatewayInfoBean> pgCache = new HashMap<>();
	
	public void initializePaymentGatewayCache(List<PaymentGatewayInfo> pgInfos) {
		for(PaymentGatewayInfo aPaymentGatewayInfo : pgInfos) {
			PaymentGatewayInfoBean pgInfoBean = new PaymentGatewayInfoBean(aPaymentGatewayInfo);
			pgCache.put(pgInfoBean.pgId, pgInfoBean);
		}
	}
	
	public Collection<PaymentGatewayInfoBean> paymentGateways() {
		return pgCache.values();
	}
	
	public PaymentGatewayInfoBean paymentGatewayInfo(String key){
		return pgCache.get(key);
	}
	
	public static void main(String[] args) {

		PaymentGatewayInfoBean pgInfoBean = new PaymentGatewayInfoBean();
		PaymentGatewayCache pgCache = new PaymentGatewayCache();
		pgCache.pgCache.put("one", pgInfoBean);
		List<PaymentGatewayInfoBean> beans = new ArrayList<>(pgCache.paymentGateways());
		beans.add(new PaymentGatewayInfoBean());
		System.out.println(beans.size());
		
		List<PaymentGatewayInfoBean> beansOther = new ArrayList<>(pgCache.paymentGateways());
		System.out.println(beansOther.size());
	}
}
