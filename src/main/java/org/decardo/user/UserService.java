package org.decardo.user;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.decardo.exception.EntityNotFoundException;
import org.decardo.exception.ExistObjectException;
import org.decardo.exception.ValidationException;
import org.decardo.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2023/11/12
 */

@Slf4j
@Service
public class UserService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserDTOValidator userDTOValidator;

	@Autowired
	private JwtUtils jwtUtils;

	public User findByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isEmpty()) {
			throw new EntityNotFoundException(User.class, username);
		}
		return user.get();
	}

	public User findById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isEmpty()) {
			throw new EntityNotFoundException(User.class, userId);
		}
		return user.get();
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public void delete(Long userId) {
		List<User> users = userRepository.findAllById(List.of(userId));
		if (users.size() != 1) {
			log.warn("User not found by id {}", userId);
			return;
		}
		userRepository.delete(users.get(0));
	}

	public User getUser(Authentication authentication) {
		return findByUsername(authentication.getName());
	}

	public User login(@Valid UserDTO userDTO) {
		validation(userDTO);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		return User.builder()
				.id(Long.valueOf(userDetails.getId()))
				.username(userDetails.getUsername())
				.email(userDetails.getEmail())
				.build();
	}

	public User signup(@Valid UserDTO userDTO) {
		validation(userDTO);
		if (Boolean.TRUE.equals(userRepository.existsByUsername(userDTO.getUsername()))) {
			throw new ExistObjectException(User.class, "username");
		}
		if (Boolean.TRUE.equals(userRepository.existsByEmail(userDTO.getEmail()))) {
			throw new ExistObjectException(User.class, "email");
		}
		User user = User.builder()
				.username(userDTO.getUsername())
				.email(userDTO.getEmail())
				.password(encoder.encode(userDTO.getPassword()))
				.build();
		user = userRepository.save(user);
		return user;
	}

	public String generateJwt(UserDTO userDTO) {
		return jwtUtils.generateJwtCookie(userDTO.getUsername()).toString();
	}

	public String cleanJwt() {
		return jwtUtils.getCleanJwtCookie().toString();
	}

	private void validation(UserDTO userDTO) {
		Set<ConstraintViolation<UserDTO>> violations = userDTOValidator.validate(userDTO);
		if (!violations.isEmpty()) {
			String errorMessage = userDTOValidator.getValidationErrorMessage(violations);
			throw new ValidationException(errorMessage);
		}
	}
}
