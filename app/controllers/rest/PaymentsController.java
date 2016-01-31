package controllers.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import controllers.base.BaseController;
import controllers.requestdto.PGRequestDto;
import controllers.requestdto.PaymentRequestDto;
import controllers.responsedto.ErrorResponse;
import controllers.responsedto.PGListResponse;
import controllers.responsedto.PGResponseDto;
import controllers.responsedto.PaymentListResponse;
import controllers.responsedto.PaymentResponseDto;
import gateway.bean.PaymentGatewayInfoBean;
import gateway.bean.PaymentGatewayResultBean;
import gateway.bean.PaymentsBean;
import gateway.lifecycle.PaymentGateway;
import gateway.lifecycle.PaymentGatewayCache;
import gateway.lifecycle.PaymentGatewayManager;
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
public class PaymentsController extends BaseController{

	@Inject
	ServicesFactory serviceFactory;

	@BodyParser.Of(BodyParser.Json.class)
	public Result listPaymentGateways() {
		
		PGListResponse response = null;
		PaymentGatewayCache pgCache = PaymentGatewayCache.getInstance();
		List<PaymentGatewayInfoBean> paymentGatewaysList = new ArrayList<>(pgCache.paymentGateways());
		response = new PGListResponse(paymentGatewaysList);
		return convertObjectToJsonResponse(response);
		
	}
	
	/**
	 * TODO : validate if the user/token is valid
	 * @return
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public Result paymentGatewayUrl() {
		
		PGResponseDto response = null;
		try {
			PGRequestDto request = convertRequestBodyToObject(request().body(), PGRequestDto.class);
			PaymentGatewayManager manager = PaymentGatewayManager.getInstance();
			PaymentGateway paymentGateway = manager.paymentGatewayInstance(request.pgId);
			
			PaymentGatewayResultBean pgResult = paymentGateway.result();
			response = new PGResponseDto(request.token, pgResult.paymentGatewayUrl, pgResult.callbackUrl);
			
		} catch (BaseException ex) {
			ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
			return errorObjectToJsonResponse(errorResponse);
		} catch (Exception e) {
			ErrorResponse errorResponse = unknownErrorResponse();
			return errorObjectToJsonResponse(errorResponse);
		}
		return convertObjectToJsonResponse(response);
	}
	
	
	/**
	 * TODO : validate if the user/token is valid
	 * @return
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public Result insertPayment() {
		
		PaymentRequestDto request;
		PaymentResponseDto response;
		try {
			request = convertRequestBodyToObject(request().body(), PaymentRequestDto.class);
			Payments payment = serviceFactory.paymentService.insertIntoPayments(request.pgReferenceId);
			response = new PaymentResponseDto(request.token, payment.paymentId);
			
		} catch (BaseException ex) {
			ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
			return errorObjectToJsonResponse(errorResponse);
		} catch (Exception e) {
			ErrorResponse errorResponse = unknownErrorResponse();
			return errorObjectToJsonResponse(errorResponse);
		}
		return convertObjectToJsonResponse(response);
	}
	
	/**
	 * TODO : validate if the user/token is valid
	 * @return
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public Result displayPayments() {
		
		PGRequestDto request;
		PaymentListResponse response = null;
		try {
			request = convertRequestBodyToObject(request().body(), PGRequestDto.class);
			List<Payments> payments = serviceFactory.paymentService.findAllPayments();
			response = new PaymentListResponse(request.token, convertToPaymentBean(payments));
		} catch (BaseException ex) {
			ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
			return errorObjectToJsonResponse(errorResponse);
		} catch (Exception e) {
			ErrorResponse errorResponse = unknownErrorResponse();
			return errorObjectToJsonResponse(errorResponse);
		}
		return convertObjectToJsonResponse(response);
	}

	public Result payPayments() {
		RequestBody body = request().body();
		Map<String, String[]> formFields = body.asFormUrlEncoded();
		String key, txnid, amount, productinfo, firstname, email, salt, sep;
		sep = "|";
		key = "gtKFFx";
		txnid = "TEST-2";
		amount = formFields.get("amount")[0];
		productinfo = formFields.get("productinfo")[0];
		firstname = formFields.get("firstname")[0];
		email = formFields.get("email")[0];
		salt = "eCwWELxi";
		String hashString = key + sep + txnid + sep + amount + sep + productinfo + sep + firstname + sep + email + 
				sep + sep + sep + sep + sep + sep + sep + sep + sep + sep + sep + salt;
		
		play.Logger.info("***********************" + hashString);
		String hash = sha512Digest(hashString);
		play.Logger.info("**********************" + hash);
		Map<String, String> userInfo = new HashMap<String, String>();
		for(Map.Entry<String, String[]> entry : formFields.entrySet()) {
			userInfo.put(entry.getKey(), entry.getValue()[0]);
		}
		userInfo.put("txnid", txnid);
		userInfo.put("hash",  hash);
		userInfo.put("key",  key);
		return ok(views.html.pay.render(userInfo));
	}
	
	private String sha512Digest(String str) {
		String ret;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(str.getBytes());
			
			byte[] mdbytes = md.digest();
		     
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < mdbytes.length; i++) {
	          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	       //convert the byte to hex format method 2
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<mdbytes.length;i++) {
	    	  hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
	    	}
	    	ret = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			ret = "NULL";
		}
		return ret;
	}
	
	private List<PaymentsBean> convertToPaymentBean(List<Payments> payments) {
		
		List<PaymentsBean> paymentsBean = new ArrayList<>();
		for(Payments aPayment : payments) {
			PaymentsBean bean = new PaymentsBean(aPayment);
			paymentsBean.add(bean);
		}
		return paymentsBean;
	}
	
}
