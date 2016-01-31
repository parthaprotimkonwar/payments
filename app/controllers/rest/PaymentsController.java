package controllers.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import controllers.base.BaseController;
import controllers.requestdto.PGRequestDto;
import controllers.requestdto.PaymentRequestDto;
import controllers.requestdto.PayuRequestDto;
import controllers.responsedto.ErrorResponse;
import controllers.responsedto.PGListResponse;
import controllers.responsedto.PGResponseDto;
import controllers.responsedto.PaymentListResponse;
import controllers.responsedto.PaymentResponseDto;
import controllers.responsedto.PayuResponseDto;
import gateway.bean.PaymentGatewayInfoBean;
import gateway.bean.PaymentGatewayResultBean;
import gateway.bean.PaymentsBean;
import gateway.lifecycle.PaymentGateway;
import gateway.lifecycle.PaymentGatewayCache;
import gateway.lifecycle.PaymentGatewayManager;
import models.PaymentGatewayInfo;
import models.Payments;
import play.exceptions.BaseException;
import play.mvc.BodyParser;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import services.serviceimpl.ServicesFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	@BodyParser.Of(BodyParser.Json.class)
	public Result paymentGatewayUrl() {

		PGResponseDto response = null;
		try {
			PGRequestDto request = convertRequestBodyToObject(request().body(),
					PGRequestDto.class);
			PaymentGatewayManager manager = PaymentGatewayManager.getInstance();
			PaymentGateway paymentGateway = manager
					.paymentGatewayInstance(request.pgId);

			PaymentGatewayResultBean pgResult = paymentGateway.result();
			response = new PGResponseDto(request.token,
					pgResult.paymentGatewayUrl, pgResult.callbackUrl);

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
	public Result insertPayment() {

		PaymentRequestDto request;
		PaymentResponseDto response;
		try {
			request = convertRequestBodyToObject(request().body(),
					PaymentRequestDto.class);
			Payments payment = serviceFactory.paymentService
					.insertIntoPayments(request.paymentId);
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

	@BodyParser.Of(BodyParser.Json.class)
	public Result payuRequest() {
		PayuRequestDto request;
		PayuResponseDto response;
		try {
			request = convertRequestBodyToObject(request().body(),
					PayuRequestDto.class);
			// getting the fist paymentGateway info
			PaymentGatewayInfo paymentGateway = serviceFactory.paymentGatewayInfoService
					.allPaymentGateways().get(0);
			String toHash, salt, key, txnid, hash;
			String s = "|";
			salt = paymentGateway.salt;
			key = paymentGateway.key;
			txnid = GenerateTxnId();
			serviceFactory.paymentService.insertIntoPayments(txnid);
			toHash = key + s + txnid + s + request.amount + s
					+ request.productinfo + s + request.firstname + s
					+ request.email + s + request.udf1 + s + request.udf2 + s
					+ request.udf3 + s + request.udf4 + s + request.udf5 + s
					+ s + s + s + s + s + salt;
			hash = sha512Digest(toHash);
			response = new PayuResponseDto(txnid, key, hash);
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

	private String sha512Digest(String str) {
		String ret;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(str.getBytes());

			byte[] mdbytes = md.digest();

			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < mdbytes.length; i++) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			ret = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			ret = "NULL";
		}
		return ret;
	}

	private String GenerateTxnId() {
		String txnId = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		return txnId;
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
