package validations.stepvalidators;

import validations.StepValidator;

/**
 * Checks if a number lies between a given range [min, max]
 * @author pkonwar
 *
 */
public class IntegerBetweenValidator implements StepValidator {

	private Integer value;
	private Integer minValue;
	private Integer maxValue;
	
	public IntegerBetweenValidator(Integer value, Integer minValue, Integer maxValue) {
		this.value = value;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	@Override
	public Boolean executeValidation() {
		
		if(value == null || minValue == null || maxValue == null)
			return false;
		
		if(value < minValue && value > maxValue)
			return false;
		
		return true;
	}

	@Override
	public String getStepName() {
		return "INTEGER_BETWEEN_VALIDATOR";
	}
	
	
}
