package services.serviceimpl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import models.PaymentGatewayInfo;
import play.exceptions.BaseException;
import repository.PaymentGatewayInfoRepository;
import services.service.PaymentGatewayInfoServiceI;

@Named
@Singleton
public class PaymentGatewayInfoServiceImpl implements PaymentGatewayInfoServiceI{

	@Inject
	PaymentGatewayInfoRepository paymentGatewayInfoRepository;
	
	@Override
	public List<PaymentGatewayInfo> allPaymentGateways() throws BaseException {
		return paymentGatewayInfoRepository.findAll();
	}

	@Override
	public PaymentGatewayInfo insertIntoPaymentGateway(PaymentGatewayInfo pgInfo) throws BaseException {
		return paymentGatewayInfoRepository.save(pgInfo);
	}

}
