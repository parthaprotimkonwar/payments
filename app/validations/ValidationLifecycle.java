package validations;

import java.util.Map;

import validations.responsehandlers.ValidationResponse;

/**
 * Lifecycle of validation
 * @author pkonwar
 *
 */
public interface ValidationLifecycle {

	/**
	 * Method to validate with additional rules applied to the object
	 * @param additionalRules
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws Exception
	 */
	public ValidationResponse validate(Map<Fields, StepValidator[]> additionalRules) throws NoSuchFieldException, SecurityException, Exception;
	
	/**
	 * Method to check if the fields mentioned is mandatory
	 * @param object
	 * @param fields
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws Exception
	 */
	public ValidationResponse checkForMandatoryFields(Object object, Fields... fields) throws NoSuchFieldException, SecurityException, Exception;
	
}
