package controllers.bootstrap;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import gateway.lifecycle.PaymentGatewayCache;
import models.PaymentGatewayInfo;
import play.exceptions.BaseException;
import services.serviceimpl.ServicesFactory;

@Named
@Singleton
public class InitializePGCache {
	
	@Inject
	ServicesFactory servicesFactory;

	@PostConstruct
	void initializeCache() {
		try {
			
			//PaymentGatewayInfo pgInfo = new PaymentGatewayInfo("PAYU", "Pay U", "", "", "gtKFFx", "eCwWELxi", "", "ACTIVE", "http://ec2-52-37-173-141.us-west-2.compute.amazonaws.com:8080/frugalbin/jsp/success.jsp", "http://www.google.com", "http://www.flipkart.com");
			//PaymentGatewayInfo pgInfo = new PaymentGatewayInfo("PAYU", "Pay U", "", "", "gtKFFx", "eCwWELxi", "", "ACTIVE", "http://localhost:8080/FrugalbinUI/jsp/success.jsp", "http://www.google.com", "http://www.flipkart.com");
			
			
			PaymentGatewayInfo pgInfo = new PaymentGatewayInfo("PAYU", "Pay U", "", "", "gtKFFx", "eCwWELxi", "", "ACTIVE", "http://ec2-54-191-122-205.us-west-2.compute.amazonaws.com:8080/frugalbin/jsp/success.jsp", "http://www.google.com", "http://www.flipkart.com");
			
			servicesFactory.paymentGatewayInfoService.insertIntoPaymentGateway(pgInfo);
			List<PaymentGatewayInfo> pgInfos = servicesFactory.paymentGatewayInfoService.allPaymentGateways();
			PaymentGatewayCache.getInstance().initializePaymentGatewayCache(pgInfos);
			System.out.println("Cache initialized");
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}
}
