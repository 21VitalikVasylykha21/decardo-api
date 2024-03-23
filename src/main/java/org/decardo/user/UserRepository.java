package org.decardo.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2023/05/29
 */
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
