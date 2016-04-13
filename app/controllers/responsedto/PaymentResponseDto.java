package controllers.responsedto;

import java.io.Serializable;

public class PaymentResponseDto implements Serializable {

	public PaymentResponseDto() {
	}
	
	public String pgid;		//payment gateway id
	public String mihpayid;
	public String mode;
	public String status;
	public String unmappedstatus;
	public String key;
	public String txnid;
	public String amount;
	public String cardCategory;
	public String discount;
	public String net_amount_debit;
	public String addedon;
	public String productinfo;
	public String firstname;
	public String lastname;
	public String address1;
	public String address2;

	public String city;
	public String state;
	public String country;
	public String zipcode;
	public String email;
	public String phone;
	public String udf1;
	public String udf2;
	public String udf3;
	public String udf4;
	public String udf5;
	public String udf6;
	public String udf7;
	public String udf8;
	public String udf9;
	public String udf10;
	public String hash;

	public String field1;
	public String field2;
	public String field3;
	public String field4;
	public String field5;
	public String field6;
	public String field7;

	public String field8;
	public String field9;
	public String payment_source;
	public String PG_TYPE;
	public String bank_ref_num;
	public String bankcode;
	public String error;

	public String error_Message;
	public String name_on_card;
	public String cardnum;
	public String cardhash;

	public String issuing_bank;
	public String card_type;

}
