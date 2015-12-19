package services.serviceimpl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import services.service.PaymentGatewayInfoServiceI;
import services.service.PaymentsServiceI;

@Named
@Singleton
public class ServicesFactory {

	@Inject
	public PaymentsServiceI paymentService;
	
	@Inject
	public PaymentGatewayInfoServiceI paymentGatewayInfoService;
}
