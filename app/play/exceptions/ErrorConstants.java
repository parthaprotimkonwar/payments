package play.exceptions;

/**
 * Application Error Handled constants. 
 * @author pkonwar
 */
public enum ErrorConstants {

	// Error Code Series : 4XX : Client Side Error (Frontend Error)
	NO_SUCH_USER_TYPE("401", "No such Usertype."),
	INVALID_LOGIN("402", "Invalid Username or Password."),
	INVALID_REQUEST_DATA("403", "Invalid Request Data"),
	INVALID_RESPONSE_FORMAT("404", "Invalid Response data"),
	DUPLICATE_EMAIL_ID("406", "Email Id already in use"),
	INVALID_TOKEN("407", "Token not valid"),
	MANDATORY_FIELDS_NOT_PRESENT("408", "Mandatory Fields Not Present"),
	VALIDATION_EXCEPTION("409", "Validation Exception"),
	
	//Error Code Series : 5XX : Server Side Error (Backend error)
	DATA_PERSISTANT_EXCEPTION("501", "Unable to Save data"),
	DATA_FETCH_EXCEPTION("502", "Unable to Fetch data"),
	DATA_REMOVAL_EXCEPTION("503", "Unable to Delete data"),
	CONTACT_SYSTEM_ADMINISTRATOR("500", "System Error. Contact system administrator");
	
	public String errorCode;
	public String errorMessage;
	
	private ErrorConstants(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
