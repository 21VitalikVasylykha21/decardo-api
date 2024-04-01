package org.decardo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
	@Email
	@Size(max = 50)
	private String email;
	@NotBlank
	@Size(min = 3, max = 20)
	private String username;
	private String role;
	private String details;
	@NotBlank
	@Size(min = 6, max = 255)
	private String password;
}
