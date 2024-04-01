package org.decardo.worker;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/04/02
 */

@Component
public class WorkerDTOValidator {
	private final Validator validator;

	public WorkerDTOValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}

	public Set<ConstraintViolation<WorkResponseDTO>> validate(WorkResponseDTO workResponseDTO) {
		return validator.validate(workResponseDTO);
	}

	public String getValidationErrorMessage(Set<ConstraintViolation<WorkResponseDTO>> violations) {
		StringBuilder errorMessage = new StringBuilder();
		for (ConstraintViolation<WorkResponseDTO> violation : violations) {
			errorMessage.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("; ");
		}
		return errorMessage.toString();
	}
}
