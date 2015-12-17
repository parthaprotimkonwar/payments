package play.utilities;

import java.util.Date;

import javax.validation.constraints.NotNull;

import controllers.responsedto.ErrorResponse;
import play.exceptions.ErrorConstants;

public class Util {

	/**
	 * Add milliseconds to the current date.
	 * @param currentDate
	 * @param minutesToAdd
	 * @return
	 */
	public static Long addTimeToDate(@NotNull Long currentDate, @NotNull long minutesToAdd) {
		final long ONE_MINUTE_IN_MILLIS = 60000; //millisecs
		Long minutesInMillis = ONE_MINUTE_IN_MILLIS * minutesToAdd;
		
		return currentDate + minutesInMillis;
	}
	
	/**
	 * Return the currentDate in Milliseconds
	 * @return
	 */
	public static Long getSystemTimeInMillis() {
		/*@TODO : Check if  System.currentTimeMillis() is faster than new Date().getTime()*/ 
		return System.currentTimeMillis();
	}
	
	/**
	 * Check whether the #timestamp is valid or not
	 * @param timestamp
	 * @param minutes
	 * @return
	 */
	public static Boolean validTime(Long timestamp, int minutes) {
		final long ONE_MINUTE_IN_MILLIS = 60000; //millisecs
		return getSystemTimeInMillis() < timestamp + (ONE_MINUTE_IN_MILLIS * minutes);
	}
	
	/**
	 * Generating Error Response
	 * @param errorConstants
	 * @return
	 */
	public static ErrorResponse generateErrorResponse(ErrorConstants errorConstants) {
		return new ErrorResponse(errorConstants.errorCode, errorConstants.errorMessage);
	}
	
	public static void main(String[] args) {
		
		Long time = System.currentTimeMillis() + 60000;
		Date now = new Date(time);
		Date then = new Date();
		System.out.println(now);
		System.out.println(then);
	}
}
