package org.decardo.worker;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.decardo.comment.CommentDTO;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/03/24
 */
@Getter
@Setter
@Builder
public class WorkResponseDTO {
	private String id;
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotBlank
	private String fileUrl;
	@NotBlank
	private String author;
	private String rating;
	private List<CommentDTO> comments;
}
