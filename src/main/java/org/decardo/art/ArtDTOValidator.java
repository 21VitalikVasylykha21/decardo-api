package org.decardo.art;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.decardo.art.model.ModelFormat;
import org.decardo.exception.ValidationException;
import org.springframework.stereotype.Component;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/04/02
 */

@Component
public class ArtDTOValidator {
	private final Validator validator;

	public ArtDTOValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}

	public void validate(ArtInputDTO artInputDTO) {
		try {
			ModelFormat.valueOf(artInputDTO.getFormat());
		} catch (Exception exception) {
			throw new ValidationException("Art 3D model format is invalidate");
		}
		Set<ConstraintViolation<ArtInputDTO>> violations = validator.validate(artInputDTO);
		if (!violations.isEmpty()) {
			String errorMessage = getValidationErrorMessage(violations);
			throw new ValidationException(errorMessage);
		}
	}

	public String getValidationErrorMessage(Set<ConstraintViolation<ArtInputDTO>> violations) {
		StringBuilder errorMessage = new StringBuilder();
		for (ConstraintViolation<ArtInputDTO> violation : violations) {
			errorMessage.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("; ");
		}
		return errorMessage.toString();
	}
}
