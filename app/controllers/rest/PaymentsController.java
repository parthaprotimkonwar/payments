package controllers.rest;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import play.mvc.BodyParser;
import play.mvc.Result;
import services.serviceimpl.ServicesFactory;

@Named
@Singleton
public class PaymentsController {

	@Inject
	ServicesFactory serviceFactory;

	/**
	 * TODO : Write impl
	 * @return
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public Result paymentGatewayUrl() {
		return null;
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
