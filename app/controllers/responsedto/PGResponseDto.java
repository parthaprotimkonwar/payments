package controllers.responsedto;

public class PGResponseDto {

	public PGResponseDto(String tokenId, String pgUrl, String callbackUrl) {
		this.tokenId = tokenId;
		this.pgUrl = pgUrl;
		this.callbackUrl = callbackUrl;
	}
	
	public String tokenId;
	public String pgUrl;
	public String callbackUrl;
	
}
