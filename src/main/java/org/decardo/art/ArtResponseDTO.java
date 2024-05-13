package org.decardo.art;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.decardo.comment.CommentDTO;
import org.decardo.art.model.ModelSettingsDto;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/03/24
 */
@Getter
@Setter
@Builder
public class ArtResponseDTO {
	private String id;
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotBlank
	private String owner;
	private String rating;
	private String model;
	private String preview;
	@NotBlank
	private String format;
	@NotBlank
	private ModelSettingsDto settings;
	@NotBlank
	private List<String> tags;
	private List<CommentDTO> comments;
}
