package controllers.bootstrap;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import gateway.cache.PaymentGatewayCache;
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
			List<PaymentGatewayInfo> pgInfos = servicesFactory.paymentGatewayInfoService.allPaymentGateways();
			PaymentGatewayCache.getInstance().initializePaymentGatewayCache(pgInfos);
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}
}
