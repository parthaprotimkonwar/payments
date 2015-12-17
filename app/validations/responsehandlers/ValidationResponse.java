package validations.responsehandlers;

import java.io.Serializable;
import java.util.List;

public class ValidationResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean isValidated;
	private String errorCode;
	private List<String> errorMessage;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public List<String> getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Boolean isValidated() {
		return isValidated;
	}
	
	public ValidationResponse(Boolean isValidated, String errorCode, List<String> errorMessage) {
		this.isValidated = isValidated;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("isValidated : ").append(isValidated).append("\n");
		if(errorCode != null && errorCode.trim().length() > 0) {
			sb.append("errorCode : ").append(errorCode).append("\n");
			sb.append("[").append("\n");
			for(String error : errorMessage) {
				sb.append("errorMessage: ").append(error).append(",").append("\n");
			}
			sb.append("]").append("\n");
		}
		return sb.toString();
	}
	
}
