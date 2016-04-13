package controllers.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.frugalbin.common.dto.request.payment.PaymentRequestDto;
import com.frugalbin.common.dto.response.payment.PaymentResponseDto;

import controllers.base.BaseController;
import controllers.requestdto.PGRequestDto;
import controllers.responsedto.ErrorResponse;
import controllers.responsedto.PGListResponse;
import controllers.responsedto.PaymentListResponse;
import gateway.bean.PaymentGatewayInfoBean;
import gateway.bean.PaymentsBean;
import gateway.lifecycle.PaymentGateway;
import gateway.lifecycle.PaymentGatewayCache;
import gateway.lifecycle.PaymentGatewayManager;
import models.Payments;
import play.exceptions.BaseException;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.serviceimpl.ServicesFactory;
import utilities.AppConstants;

@Named
@Singleton
public class PaymentsController extends BaseController {

	@Inject
	ServicesFactory serviceFactory;

	@BodyParser.Of(BodyParser.Json.class)
	public Result listPaymentGateways() {

		PGListResponse response = null;
		PaymentGatewayCache pgCache = PaymentGatewayCache.getInstance();
		List<PaymentGatewayInfoBean> paymentGatewaysList = new ArrayList<>(
				pgCache.paymentGateways());
		response = new PGListResponse(paymentGatewaysList);
		return convertObjectToJsonResponse(response);

	}

	/**
	 * TODO : validate if the user/token is valid
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@BodyParser.Of(BodyParser.Json.class)
	public Result paymentGatewayUrl() throws BaseException {

		Map<String, String> responseParams;
		String responseHtml;
		try {
			PaymentRequestDto paymentRequest = convertRequestBodyToObject(request().body(), PaymentRequestDto.class);
			PaymentGatewayManager manager = PaymentGatewayManager.getInstance();
			Payments payment = new Payments();
			payment.status = AppConstants.Status.ACTIVE.name();
			payment = serviceFactory.paymentService.insertIntoPayments(payment);
			PaymentGateway paymentGateway = manager.paymentGatewayInstance(paymentRequest.pgId);
			responseParams = paymentGateway.result(paymentRequest, "FRUGAL1" + payment.id);
			responseHtml = paymentGateway.generateStringHtmlForm(paymentRequest, responseParams);
		} catch (Exception e) {
			ErrorResponse errorResponse = unknownErrorResponse();
			return errorObjectToJsonResponse(errorResponse);
		}
		response().setHeader("Access-Control-Allow-Origin", request().getHeader("Origin"));
		return ok(responseHtml);
	}

	public Result enablecors() {
		
		System.out.println("inside enable cors");
		response().setHeader("Access-Control-Allow-Origin", request().getHeader("Origin"));
		response().setHeader("Access-Control-Allow-Methods", "HEAD,GET,PUT,DELETE,OPTIONS");
		response().setHeader("Access-Control-Max-Age", "10000");
		response().setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Referrer, User-Agent");
		
		return ok();
	}
	
	@BodyParser.Of(BodyParser.Json.class)
	public Result validatePGResponse() {
		
		PaymentResponseDto response = null;
		try {
			response = convertRequestBodyToObject(request().body(), PaymentResponseDto.class);
			PaymentGatewayManager manager = PaymentGatewayManager.getInstance();
			PaymentGateway paymentGateway = manager.paymentGatewayInstance(response.pgid);
			Boolean status = paymentGateway.validatePGResponse(response);
			
			System.out.println("Payment Status : " + status);
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("TICKET BOOKED");
		System.out.println("Origin : " + request().getHeader("Origin"));
		
		response().setHeader("Access-Control-Allow-Origin", request().getHeader("Origin"));
		
		return ok(request().body().asJson());
	}
	
	/**
	 * TODO : validate if the user/token is valid
	 * 
	 * @return
	 */
	/*@BodyParser.Of(BodyParser.Json.class)
	public Result insertPayment() {

		PaymentRequestDto request;
		PaymentResponseDto response;
		try {
			request = convertRequestBodyToObject(request().body(),
					PaymentRequestDto.class);
			Payments payment = serviceFactory.paymentService
					.insertIntoPayments(request.pgReferenceId);
			response = new PaymentResponseDto(request.token, payment.paymentId);

		} catch (BaseException ex) {
			ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(),
					ex.getErrorMessage());
			return errorObjectToJsonResponse(errorResponse);
		} catch (Exception e) {
			ErrorResponse errorResponse = unknownErrorResponse();
			return errorObjectToJsonResponse(errorResponse);
		}
		return convertObjectToJsonResponse(response);
	}*/

	/**
	 * TODO : validate if the user/token is valid
	 * 
	 * @return
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public Result displayPayments() {

		PGRequestDto request;
		PaymentListResponse response = null;
		try {
			request = convertRequestBodyToObject(request().body(),
					PGRequestDto.class);
			List<Payments> payments = serviceFactory.paymentService
					.findAllPayments();
			response = new PaymentListResponse(request.token,
					convertToPaymentBean(payments));
		} catch (BaseException ex) {
			ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(),
					ex.getErrorMessage());
			return errorObjectToJsonResponse(errorResponse);
		} catch (Exception e) {
			ErrorResponse errorResponse = unknownErrorResponse();
			return errorObjectToJsonResponse(errorResponse);
		}
		return convertObjectToJsonResponse(response);
	}

	private List<PaymentsBean> convertToPaymentBean(List<Payments> payments) {

		List<PaymentsBean> paymentsBean = new ArrayList<>();
		for (Payments aPayment : payments) {
			PaymentsBean bean = new PaymentsBean(aPayment);
			paymentsBean.add(bean);
		}
		return paymentsBean;
	}

}
