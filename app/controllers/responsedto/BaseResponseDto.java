package controllers.responsedto;

import java.io.Serializable;

public class BaseResponseDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String status;
	public String token;
	public ErrorResponse errorResponse;
	
	public BaseResponseDto() {}
	
	
	public BaseResponseDto(String token, String status) {
		this.status = status;
		this.token = token;
	}
	
	public BaseResponseDto(String token, String status, ErrorResponse errorResponse) {
		this.status = status;
		this.token = token;
		this.errorResponse = errorResponse;
	}
}