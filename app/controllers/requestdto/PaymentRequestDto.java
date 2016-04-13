package controllers.requestdto;

import java.io.Serializable;

public class PaymentRequestDto implements Serializable {

	public PaymentRequestDto() {
	}

	public PaymentRequestDto(String pgId, String firstName, String lastName, String phoneNo, String amount, String email, String productInfo) {
		this.pgId = pgId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.amount = amount;
		this.email = email;
		this.productInfo = productInfo;
	}

	public String token;
	public String pgId;
	public String amount;
	public String productInfo;
	public String firstName;
	public String lastName;
	public String email;
	public String phoneNo;
	public String surl;
	public String curl;
	public String furl;
	public String udf1;
	public String udf2;
	public String udf3;
	public String udf4;
	public String udf5;
}
