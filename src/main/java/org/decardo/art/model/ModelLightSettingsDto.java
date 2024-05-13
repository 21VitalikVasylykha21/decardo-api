package org.decardo.art.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU 
 * @since 2024/05/08
 */

@Getter
@Setter
@Builder
public class ModelLightSettingsDto {
	@NotBlank
	private String intensity;
	@NotBlank
	private String groundColor;
}
