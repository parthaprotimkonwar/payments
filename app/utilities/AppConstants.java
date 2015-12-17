package utilities;

import play.exceptions.BaseException;
import play.exceptions.ErrorConstants;

public class AppConstants {

	public static enum Status {
		
		ACTIVE("ACTIVE"),
		CONFIRMATION_PENDING("CONFIRMATION_PENDING"),
		SUCCESS("SUCCESS"),
		FAILURE("FAILURE");
		
		String status;
		
		private Status(String status) {
			this.status = status;
		}
		
		@Override
		public String toString() {
			return status;
		}
		
	}
	
	
	public static enum UserType {
		FRUGAL("FRUGAL"),
		SOCIAL("SOCIAL"),
		GUEST("GUEST");
		
		String userType;
		
		private UserType(String userType) {
			this.userType = userType;
		}
		
		public static UserType value(String userType) throws BaseException{
			try {
				return UserType.valueOf(userType);
			} catch (Exception ex) {
				ErrorConstants error = ErrorConstants.NO_SUCH_USER_TYPE;
				throw new BaseException(error.errorCode, error.errorMessage, ex.getCause());
			}
		}
		
		@Override
		public String toString() {
			return userType;
		}
		
	}
}
