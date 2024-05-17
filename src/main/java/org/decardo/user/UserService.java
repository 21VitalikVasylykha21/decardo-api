package org.decardo.user;

import com.google.common.base.Strings;
import jakarta.validation.ConstraintViolation;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.decardo.exception.EntityNotFoundException;
import org.decardo.exception.ExistObjectException;
import org.decardo.exception.ValidationException;
import org.decardo.firebase.FirebaseStorageService;
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

	@Autowired
	private FirebaseStorageService firebaseStorageService;

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

	public User login(UserDTO userDTO) {
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

	public User update(UserUpdateRequestDTO userUpdateRequestDTO) {
		User user = findById(Long.valueOf(userUpdateRequestDTO.getId()));
		if (userUpdateRequestDTO.getAvatar() != null) {
			user.setAvatar(firebaseStorageService.upload(userUpdateRequestDTO.getAvatar()));
		}
		if (userUpdateRequestDTO.getBannerImage() != null) {
			user.setBanner(firebaseStorageService.upload(userUpdateRequestDTO.getBannerImage()));
		}
		if (!Strings.isNullOrEmpty(userUpdateRequestDTO.getUsername())) {
			user.setUsername(userUpdateRequestDTO.getUsername());
		}
		if (!Strings.isNullOrEmpty(userUpdateRequestDTO.getContact())) {
			user.setContact(userUpdateRequestDTO.getContact());
		}
		if (!Strings.isNullOrEmpty(userUpdateRequestDTO.getDescription())) {
			user.setDetails(userUpdateRequestDTO.getDescription());
		}
		userRepository.save(user);
		return findById(user.getId());
	}

	public User signup(UserDTO userDTO) {
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

	public String generateJwt(User user) {
		return jwtUtils.generateJwtCookie(user.getUsername()).toString();
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
