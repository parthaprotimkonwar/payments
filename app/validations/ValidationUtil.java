package validations;

public class ValidationUtil {

	/**
	 * Check if a String is null or empty
	 * @param value
	 * @return
	 */
	public static Boolean stringNotNullOrEmpty(String value) {
		return value != null && value.trim().length() != 0 ? true : false;
	}
	
	/**
	 * Array of string isnot null or empty
	 * @param values
	 * @return
	 */
	public static Boolean stringsNotNullOrEmpty(String... values) {
		for(String oneValue : values) {
			boolean isNull = stringNotNullOrEmpty(oneValue);
			if(isNull == false)
				return false;
		}
		return true;
	}
}
