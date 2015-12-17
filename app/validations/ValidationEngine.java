package validations;

import static validations.ValidationConstants.JAVA_LANG_LONG;
import static validations.ValidationConstants.JAVA_LANG_STRING;
import static validations.ValidationConstants.JAVA_UTIL_DATE;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.exceptions.ErrorConstants;
import validations.responsehandlers.ValidationResponse;

/**
 * 
 * Contains all Validation Methods defined here 
 * @author pkonwar
 *
 */
public abstract class ValidationEngine implements ValidationLifecycle{

	private final Map<String, Map<String, Object>> convertObjectToMapWithFields(Object object) throws IllegalArgumentException, IllegalAccessException {
		
		Map<String, Map<String, Object>> mapRepresentation = new HashMap<>();
		
		Field[] field = object.getClass().getFields();
		for(Field f : field) {
			Class<?> clazz = f.getType();
			String name = f.getName();
			Object value = f.get(object);
			Map<String, Object> values = new HashMap<>();
			values.put(ValidationConstants.CLASS_NAME, clazz);
			values.put(ValidationConstants.VALUE, value);
			mapRepresentation.put(name, values);
		}
		return mapRepresentation;
	}
	
	public ValidationResponse validate(Map<Fields, StepValidator[]> additionalRules) throws NoSuchFieldException, SecurityException, Exception {
		boolean isValidated = true;
		List<String> errorMessage = null;
		ValidationResponse response = null;
		String errorCode = null;
		for(Map.Entry<Fields, StepValidator[]> oneFieldRules : additionalRules.entrySet()) {
			Fields keyField = oneFieldRules.getKey();
			StepValidator[] steps = oneFieldRules.getValue();
			try {
				validateAllSteps(steps);
			} catch(Exception ex) {
				isValidated = false;
				if(errorCode == null)
					errorCode = ErrorConstants.VALIDATION_EXCEPTION.errorCode;
				if(errorMessage == null)
					errorMessage = new ArrayList<>();
				String message = keyField.getStringValue() + " : " + ex.getMessage();
				errorMessage.add(message);
			}
		}
		response = new ValidationResponse(isValidated, errorCode, errorMessage);
		return response;
	}
	
	
	private void savedErrorMessage(String errorCode, String errorMessage, List<String> savedErrorMessage) {
		if(savedErrorMessage == null)
			savedErrorMessage = new ArrayList<>();
		savedErrorMessage.add(errorMessage);
	}
	private Boolean validateAllSteps(StepValidator[] steps) throws Exception{
		
		for(StepValidator oneStep : steps) {
			boolean isValid = oneStep.executeValidation();
			if(!isValid)
				throw new Exception(oneStep.getStepName() + " failed");
		}
		return true;
	}
	
	@Override
	public ValidationResponse checkForMandatoryFields(Object object, Fields... fields) throws NoSuchFieldException, SecurityException, Exception {

		Map<String, Map<String, Object>> mapRepresentation = convertObjectToMapWithFields(object);
		List<String> errorMessage = new ArrayList<>();
		ValidationResponse response = null;
		boolean isValidated = true;
		String errorCode = null;
		
		for(Fields aField : fields) {
			Map<String, Object> fieldValues = mapRepresentation.get(aField.getStringValue());
			Class<?> clazz = (Class<?>) fieldValues.get(ValidationConstants.CLASS_NAME);
			Object value = fieldValues.get(ValidationConstants.VALUE);
			Boolean isValid = false;
			String message = null;

			switch (clazz.getName()) {
				case JAVA_LANG_STRING:
					try {
						isValid = ValidationUtil.stringNotNullOrEmpty(value.toString());
						if(!isValid) { 
							isValidated = false;
							message = aField.getStringValue() + " is Null";
						}
					} catch (Exception e) {
						isValidated = false;
						message = aField + " is not a Valid String";
					}
					break;
				
				case JAVA_UTIL_DATE:
					try {
						Date actualDate = (Date) value;
					} catch (Exception e) {
						isValidated = false;
						message = aField + " is not a Valid Date";
					}
					break;
					
				case JAVA_LANG_LONG:
					try {
						Long actualLong = (Long) value;
					} catch (Exception e) {
						isValidated = false;
						message = aField + " is not a Valid Long";
					}
					break;
					
				default:
					System.out.println(clazz.getName());
					break;
			}
			
			if(!isValid) {
				if(errorCode == null)
					errorCode = ErrorConstants.VALIDATION_EXCEPTION.errorCode;
				if(errorMessage == null)
					errorMessage = new ArrayList<>();
				errorMessage.add(message);
			}
		}
		
		response = new ValidationResponse(isValidated, errorCode, errorMessage);
		/*if(!errorMessage.isEmpty()) {
			response = new ValidationResponse(false, errorMessage);
		} else {
			response = new ValidationResponse(true, null);
		}*/
		return response;
	}
}
