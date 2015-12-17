package validations.stepvalidators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import validations.StepValidator;

/**
 * Validates that the String contains a Number
 * @author pkonwar
 *
 */
public class NumberValidator implements StepValidator{

	private final String number;
	private final static String NUMBER_REGEX = "^[0-9]+$";
	private static final Pattern pattern = Pattern.compile(NUMBER_REGEX);
	
	public NumberValidator(String number) {
		this.number = number;
	}

	@Override
	public Boolean executeValidation() {
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}
	
	@Override
	public String getStepName() {
		return "NUMBER_VALIDATOR";
	}
	
	
	public static void main(String[] args) {
		String num = "123";
		NumberValidator number = new NumberValidator(num);
		System.out.println(number.executeValidation());
	}
	
}
