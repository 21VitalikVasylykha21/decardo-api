package org.decardo.art;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/05/12
 */

@Getter
@Builder
public class ArtInputDTO {
	private String id;
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotBlank
	private String owner;
	@NotBlank
	@Size(min = 3, max = 3)
	private String format;
	@NotBlank
	@Size(min = 7, max = 7)
	private String backgroundColor;
	@NotBlank
	private String ambientLightIntensity;
	@NotBlank
	private String directionalLightIntensity;
	@NotBlank
	private String hemisphereLightIntensity;
	@NotBlank
	@Size(min = 7, max = 7)
	private String hemisphereLightGroundColor;
	@NotNull
	private MultipartFile model;
	@NotNull
	private MultipartFile preview;
	private List<String> tags;
}
