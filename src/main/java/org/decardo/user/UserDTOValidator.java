package org.decardo.user;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/04/01
 */
@Component
public class UserDTOValidator {
	private final Validator validator;

	public UserDTOValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}

	public Set<ConstraintViolation<UserDTO>> validate(UserDTO userDTO) {
		return validator.validate(userDTO);
	}

	public String getValidationErrorMessage(Set<ConstraintViolation<UserDTO>> violations) {
		StringBuilder errorMessage = new StringBuilder();
		for (ConstraintViolation<UserDTO> violation : violations) {
			errorMessage.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("; ");
		}
		return errorMessage.toString();
	}
}