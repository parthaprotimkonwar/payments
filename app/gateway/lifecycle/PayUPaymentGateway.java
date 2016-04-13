package gateway.lifecycle;

import java.util.HashMap;
import java.util.Map;

import controllers.requestdto.PaymentRequestDto;
import controllers.responsedto.PaymentResponseDto;
import gateway.bean.PaymentGatewayInfoBean;
import utilities.Constants;
import utilities.HashCalculator;

public class PayUPaymentGateway implements PaymentGateway{

	private static PaymentGatewayInfoBean pgInfo = PaymentGatewayCache.getInstance().paymentGatewayInfo(Constants.PG_ID_PAYU);

	@Override
	public Map<String, String> result(PaymentRequestDto request, String txnid) {
		Map<String, String> response = new HashMap<String, String>();
		String hash, s = "|";
		String key = pgInfo.key;
		String salt = pgInfo.salt;
		String amount = request.amount;
		String productinfo = blankIfNull(request.productInfo);
		String firstname = blankIfNull(request.firstName);
		String email = blankIfNull(request.email);
		String udf1 = blankIfNull(request.udf1);
		String udf2 = blankIfNull(request.udf2);
		String udf3 = blankIfNull(request.udf3);
		String udf4 = blankIfNull(request.udf4);
		String udf5 = blankIfNull(request.udf5);
		String toHash = key + s + txnid + s + amount + s
				+ productinfo + s + firstname + s
				+ email + s + udf1 + s + udf2 + s
				+ udf3 + s + udf4 + s + udf5 + s
				+ s + s + s + s + s + salt;
		hash = HashCalculator.Sha512Digest(toHash);
		response.put("txnid", txnid);
		response.put("url", pgInfo.successUrl);
		response.put("key", pgInfo.key);
		response.put("hash", hash);
		return response;
	}
	
	
	public String generateStringHtmlForm(PaymentRequestDto request, Map<String, String> response) {
		
		String form = "<form id='formpayu' action='https://test.payu.in/_payment' method='post'>" + 
					        "<input type='' name='firstname' value='" + blankIfNull(request.firstName) + "' />" + 
					        /*"<input type='' name='lastname' value='" + request.lastName +"' />" +*/ 
					        "<input type='' name='surl' value='"+ pgInfo.successUrl +"' />" +
					        "<input type='' name='phone' value='" + blankIfNull(request.phoneNo) +"' />" +
					        "<input type='' name='key' value='" + pgInfo.key + "' />" +				//key = gtKFFx
					        "<input type='' name='hash' value = '" + response.get("hash") + "' />" + 
					        "<input type='' name='curl' value='"+ pgInfo.callbackUrl +"' />" + 
					        "<input type='' name='furl' value='"+ pgInfo.forwardUrl +"' />" + 
					        "<input type='' name='txnid' value='"+ response.get("txnid") + "' />" + 
					        "<input type='' name='productinfo' value='" + blankIfNull(request.productInfo) +"' />" + 
					        "<input type='' name='amount' value='" + request.amount + "' />" + 
					        "<input type='' name='email' value='"+ blankIfNull(request.email) +"' />" + 
					        "<input id='submitbutton' type= 'submit' value='submit'>" +
					  "</form>";
		return form;
	}
	
	@Override
	public boolean validatePGResponse(PaymentResponseDto response) {
		String key = pgInfo.key;
		String salt = pgInfo.salt;
		String status = blankIfNull(response.status);
		String udf5 = blankIfNull(response.udf5);
		String udf4 = blankIfNull(response.udf4);
		String udf3 = blankIfNull(response.udf3);
		String udf2 = blankIfNull(response.udf2);
		String udf1 = blankIfNull(response.udf1);
		
		String email = blankIfNull(response.email);
		String firstname = blankIfNull(response.firstname);
		String productinfo = blankIfNull(response.productinfo);
		String amount = blankIfNull(response.amount);
		String txnid = blankIfNull(response.txnid);
		
		StringBuffer sb = new StringBuffer();
		sb.append(salt).append("|").append(status).append("||||||").append(udf5).append("|").append(udf4).append("|").append(udf3).append("|").append(udf2).append("|").append(udf1).append("|")
							.append(email).append("|").append(firstname).append("|").append(productinfo).append("|").append(amount).append("|").append(txnid).append("|").append(key);
		
		String computedHash = HashCalculator.Sha512Digest(sb.toString());
		
		if(response.hash != null && response.hash.equals(computedHash)) {
			return true;
		}
		return false;
	}
	
	private String blankIfNull(String aString) {
		return aString == null || aString.trim().length() == 0 ? "" : aString; 
	}

}
