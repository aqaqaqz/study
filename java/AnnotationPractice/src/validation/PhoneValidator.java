package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import annotation.FieldAnnotation;
import annotation.PhoneValidationAnnotation;

public class PhoneValidator  implements ConstraintValidator<PhoneValidationAnnotation, String>{

	final Pattern NUM_PATTERN = Pattern.compile("^[0-9]+$");

	@Override
	public void initialize(PhoneValidationAnnotation arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isValid(String phone, ConstraintValidatorContext arg1) {
		if (phone == null || "".equals(phone)) 
			return false;

		Matcher m = NUM_PATTERN.matcher(phone);
		return m.find();
	}

}
