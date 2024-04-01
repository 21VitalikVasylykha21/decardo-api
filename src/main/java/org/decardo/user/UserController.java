package org.decardo.user;

import java.util.List;
import org.decardo.response.ListObjectResponse;
import org.decardo.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/03/31
 */

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userService;

	@PostMapping("/auth/login")
	public ResponseEntity login(@RequestBody UserDTO userDTO) {
		try {
			User user = userService.login(userDTO);
			ListObjectResponse<UserDTO> response = new ListObjectResponse<>(List.of(userMapper.convert(user)));
			String jwt = userService.generateJwt(userDTO);
			return org.springframework.http.ResponseEntity.ok()
					.header(HttpHeaders.SET_COOKIE, jwt).body(response);
		} catch (Exception e) {
			return org.springframework.http.ResponseEntity.badRequest()
					.body(new MessageResponse<>(HttpStatus.BAD_REQUEST, e.getMessage()));
		}
	}

	@PostMapping("/auth/signup")
	public ResponseEntity signup(@RequestBody UserDTO userDTO) {
		try {
			User user = userService.signup(userDTO);
			ListObjectResponse<UserDTO> response = new ListObjectResponse<>(List.of(userMapper.convert(user)));
			String jwt = userService.generateJwt(userDTO);
			return org.springframework.http.ResponseEntity.ok()
					.header(HttpHeaders.SET_COOKIE, jwt).body(response);
		} catch (Exception e) {
			return org.springframework.http.ResponseEntity.badRequest()
					.body(new MessageResponse<>(HttpStatus.BAD_REQUEST, e.getMessage()));
		}
	}

	@PostMapping("/auth/logout")
	public ResponseEntity<?> logout() {
		String jwt = userService.cleanJwt();
		return ResponseEntity.ok()
				.header(HttpHeaders.SET_COOKIE, jwt)
				.body(new MessageResponse<>("Successfully logged out"));
	}

	@GetMapping
	public ListObjectResponse<UserDTO> findAll() {
		return new ListObjectResponse<>(
				userService.findAll().stream()
						.map(userMapper::convert)
						.toList());
	}

	@DeleteMapping("/{userId}")
	public MessageResponse delete(@PathVariable Long userId) {
		userService.delete(userId);
		return new MessageResponse<>("User successfully removed");
	}
}