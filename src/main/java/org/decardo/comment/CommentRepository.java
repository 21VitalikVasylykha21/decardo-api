package org.decardo.comment;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/04/01
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByUserId(Long userId);

	List<Comment> findByArtId(Long artId);
}
