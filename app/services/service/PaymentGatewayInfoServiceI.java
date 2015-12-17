package services.service;

import java.util.List;

import models.PaymentGatewayInfo;
import play.exceptions.BaseException;

public interface PaymentGatewayInfoServiceI {

	List<PaymentGatewayInfo> allPaymentGateways() throws BaseException;
	
	PaymentGatewayInfo insertIntoPaymentGateway(PaymentGatewayInfo pgInfo) throws BaseException;
}
