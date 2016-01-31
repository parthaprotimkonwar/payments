package controllers.responsedto;

public class PayuResponseDto {
	public String txnid;
	public String key;
	public String hash;
	
	public PayuResponseDto(String txnid, String key, String hash) {
		this.txnid = txnid;
		this.key = key;
		this.hash = hash;
	}
}
