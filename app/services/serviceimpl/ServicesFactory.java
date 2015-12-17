package services.serviceimpl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import services.service.PaymentGatewayInfoServiceI;
import services.service.PaymentsServiceI;
import services.service.PersonServiceI;

@Named
@Singleton
public class ServicesFactory {

	@Inject
	public PersonServiceI personService;
	
	@Inject
	public PaymentsServiceI paymentService;
	
	@Inject
	public PaymentGatewayInfoServiceI paymentGatewayInfoService;
}
