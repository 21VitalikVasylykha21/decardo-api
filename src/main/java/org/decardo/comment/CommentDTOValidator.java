package org.decardo.comment;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.decardo.user.UserDTO;
import org.springframework.stereotype.Component;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/04/02
 */

@Component
public class CommentDTOValidator {

	private final Validator validator;

	public CommentDTOValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}

	public Set<ConstraintViolation<CommentDTO>> validate(CommentDTO commentDTO) {
		return validator.validate(commentDTO);
	}

	public String getValidationErrorMessage(Set<ConstraintViolation<CommentDTO>> violations) {
		StringBuilder errorMessage = new StringBuilder();
		for (ConstraintViolation<CommentDTO> violation : violations) {
			errorMessage.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("; ");
		}
		return errorMessage.toString();
	}
}
