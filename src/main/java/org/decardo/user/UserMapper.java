package org.decardo.user;

import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2023/11/12
 */

@Service
public class UserMapper {

	public UserDTO convert(User user) {
		return UserDTO.builder()
				.id(String.valueOf(user.getId()))
				.username(user.getUsername())
				.role(user.getRole().name().toLowerCase())
				.details(user.getDetails())
				.email(user.getEmail())
				.build();
	}
}