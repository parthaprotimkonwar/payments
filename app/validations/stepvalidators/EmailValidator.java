package validations.stepvalidators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import validations.StepValidator;

/**
 * Validates a proper email
 * @author pkonwar
 */
public class EmailValidator implements StepValidator {

	private final String emailId;
	private final static String EMAIL_REGEX = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);
	
	public EmailValidator(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public Boolean executeValidation() {
		
		Matcher matcher = pattern.matcher(emailId);
		return matcher.matches();
		
	}

	@Override
	public String getStepName() {
		return "Email Validator";
	}
	
	public static void main(String[] args) {
		
		String emailId = "partha@gmail.com.com";
		EmailValidator emailValidator = new EmailValidator(emailId);
		System.out.println(emailValidator.executeValidation());
	}
	
}
