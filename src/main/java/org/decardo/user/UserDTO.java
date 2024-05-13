package org.decardo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.decardo.art.ArtResponseDTO;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2023/05/29
 */
@Getter
@Setter
@Builder
public class UserDTO {
	private String id;
	private String role;
	@Email
	@Size(max = 50)
	private String email;
	@NotBlank
	@Size(min = 3, max = 20)
	private String username;
	private String description;
	@NotBlank
	@Size(min = 6, max = 255)
	private String password;
	private String avatar;
	private String contact;
	private String bannerImage;
	private boolean isMyProfile;
	private List<ArtResponseDTO> wishlist;
	private List<ArtResponseDTO> arts;
}
