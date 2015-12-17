package validations.stepvalidators;

import validations.StepValidator;

/**
 * Validates the max length of a String
 * @author pkonwar
 *
 */
public class StringLengthValidator implements StepValidator{

	private final String theString;
	private final Integer maxLength;
	
	public StringLengthValidator(String theString, Integer maxLength) {
		this.theString = theString;
		this.maxLength = maxLength;
	}

	@Override
	public Boolean executeValidation() {
		
		if(theString == null || theString.length() > maxLength)
			return false;
		
		return true;
	}
	
	@Override
	public String getStepName() {
		return "STRING_LENGTH_VALIDATOR";
	}
	
	
}
