package controllers.rest;

import gateway.bean.PaymentGatewayInfoBean;
import gateway.bean.PaymentsBean;
import gateway.lifecycle.PaymentGateway;
import gateway.lifecycle.PaymentGatewayCache;
import gateway.lifecycle.PaymentGatewayManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Payments;
import play.exceptions.BaseException;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.serviceimpl.ServicesFactory;
import utilities.AppConstants;
import controllers.base.BaseController;
import controllers.requestdto.PGRequestDto;
import controllers.requestdto.PaymentRequestDto;
import controllers.responsedto.ErrorResponse;
import controllers.responsedto.PGListResponse;
import controllers.responsedto.PaymentListResponse;
import controllers.responsedto.PaymentResponseDto;

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

		Map<String, String> request;
		Map<String, String> response;
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = request().body().asJson();
			request = mapper.convertValue(jsonNode, Map.class);
			PaymentGatewayManager manager = PaymentGatewayManager.getInstance();
			Payments payment = new Payments();
			payment.status = AppConstants.Status.ACTIVE.name();
			payment = serviceFactory.paymentService.insertIntoPayments(payment);
			PaymentGateway paymentGateway = manager
					.paymentGatewayInstance(request.get("pgId"));
			response = paymentGateway.result(request, payment.paymentId.toString());

		} catch (Exception e) {
			ErrorResponse errorResponse = unknownErrorResponse();
			return errorObjectToJsonResponse(errorResponse);
		}
		return convertObjectToJsonResponse(response);
	}

	/**
	 * TODO : validate if the user/token is valid
	 * 
	 * @return
	 */
	@BodyParser.Of(BodyParser.Json.class)
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
	}

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
