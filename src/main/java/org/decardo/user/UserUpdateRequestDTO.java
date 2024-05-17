package org.decardo.user;

import jakarta.validation.constraints.NotBlank;
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
public class UserUpdateRequestDTO {

	private String id;
	private MultipartFile avatar;
	private MultipartFile bannerImage;
	@NotBlank
	private String contact;
	@NotBlank
	private String username;
	@NotBlank
	private String description;
}
