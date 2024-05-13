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
public class ModelSettingsDto {
	@NotBlank
	private ModelLightSettingsDto ambientLight;
	@NotBlank
	private ModelLightSettingsDto directionalLight;
	@NotBlank
	private ModelLightSettingsDto hemisphereLight;
	@NotBlank
	private String backgroundColor;
}
