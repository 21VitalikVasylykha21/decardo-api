package org.decardo.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/04/01
 */

@Getter
@Builder
public class CommentDTO {

	private String id;
	@NotNull
	private String userId;
	@NotNull
	private String workId;
	@NotBlank
	private String comment;
	private String date;
}
