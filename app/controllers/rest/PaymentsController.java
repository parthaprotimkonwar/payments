package controllers.rest;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import controllers.base.BaseController;
import controllers.requestdto.PGRequestDto;
import controllers.responsedto.PGResponseDto;
import gateway.lifecycle.PaymentGateway;
import gateway.lifecycle.PaymentGatewayManager;
import play.exceptions.BaseException;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.serviceimpl.ServicesFactory;

@Named
@Singleton
public class PaymentsController extends BaseController{

	@Inject
	ServicesFactory serviceFactory;

	@BodyParser.Of(BodyParser.Json.class)
	public Result paymentGatewayUrl() {
		
		PGResponseDto response = null;
		try {
			PGRequestDto request = convertRequestBodyToObject(request().body(), PGRequestDto.class);
			PaymentGatewayManager manager = PaymentGatewayManager.getInstance();
			PaymentGateway paymentGateway = manager.paymentGatewayInstance(request.pgId);
			
			String paymentGatewayURL = paymentGateway.paymentGatewayUrl();
			String callbackUrl = paymentGateway.callbackUrl();
			
			response = new PGResponseDto(request.tokenId, paymentGatewayURL, callbackUrl);
			
		} catch (BaseException e) {
			e.printStackTrace();
		}
		return convertObjectToJsonResponse(response);
	}
	
	/**
	 * TODO : Write impl
	 * @return
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public Result insertPayment() {
		return null;
	}
	
	/**
	 * TODO : Write impl
	 * @return
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public Result displayPayments() {
		return null;
	}
}
