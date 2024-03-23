package org.decardo.user;

import java.util.Optional;
import org.decardo.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2023/11/12
 */

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User findByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isEmpty()) {
			throw new EntityNotFoundException(User.class, username);
		}
		return user.get();
	}

	public User getUser(Authentication authentication) {
		return findByUsername(authentication.getName());
	}
}
