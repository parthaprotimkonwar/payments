package validations;

/**
 * Interface for Validation Steps
 * All validators eg. EmailValidator/AlphanumericValidator should implement this interface
 * @author pkonwar
 *
 */
public interface StepValidator {

	Boolean executeValidation();
	
	String getStepName();
}
