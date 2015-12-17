package controllers.responsedto;

import java.io.Serializable;

public class UsersResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for sending Success Response
	 * @param userId
	 * @param status
	 */
	public UsersResponseDto(String token, String status) {
		this.token = token;
		this.status = status;
	}
	
	public String status;
	public String token;
	
}
