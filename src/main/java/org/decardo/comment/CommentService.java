package org.decardo.comment;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.decardo.exception.EntityNotFoundException;
import org.decardo.exception.ValidationException;
import org.decardo.user.User;
import org.decardo.user.UserService;
import org.decardo.worker.Work;
import org.decardo.worker.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/04/01
 */

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private CommentDTOValidator commentDTOValidator;

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private WorkService workService;

	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	public List<Comment> findByUserId(Long userId) {
		return commentRepository.findByUserId(userId);
	}

	public List<Comment> findByWorkId(Long workId) {
		return commentRepository.findByWorkId(workId);
	}

	public Comment findById(Long id) {
		Optional<Comment> comment = commentRepository.findById(id);
		if (comment.isEmpty()) {
			throw new EntityNotFoundException(Comment.class, id);
		}
		return comment.get();
	}

	@Transactional
	public Comment create(CommentDTO commentDTO) {
		validation(commentDTO);
		User user = userService.findById(Long.valueOf(commentDTO.getUserId()));
		Work work = workService.findById(Long.valueOf(commentDTO.getWorkId()));
		Comment comment = commentMapper.convert(commentDTO, user, work);
		return commentRepository.save(comment);
	}

	@Transactional
	public Comment update(CommentDTO commentDTO) {
		validation(commentDTO);
		Comment comment = findById(Long.valueOf(commentDTO.getId()));
		comment.setComment(commentDTO.getComment());
		return commentRepository.save(comment);
	}

	@Transactional
	public void delete(Long id) {
		commentRepository.deleteById(id);
	}

	private void validation(CommentDTO commentDTO) {
		Set<ConstraintViolation<CommentDTO>> violations = commentDTOValidator.validate(commentDTO);
		if (!violations.isEmpty()) {
			String errorMessage = commentDTOValidator.getValidationErrorMessage(violations);
			throw new ValidationException(errorMessage);
		}
	}
}
