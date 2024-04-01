package org.decardo.worker;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.decardo.exception.EntityNotFoundException;
import org.decardo.exception.ValidationException;
import org.decardo.rating.RatingService;
import org.decardo.user.User;
import org.decardo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/03/24
 */
@Service
public class WorkService {
	@Autowired
	private WorkRepository workRepository;

	@Autowired
	private RatingService ratingService;

	@Autowired
	private UserService userService;

	@Autowired
	private WorkerDTOValidator workDTOValidator;

	public List<Work> findAll() {
		List<Work> works = workRepository.findAll();
		works.forEach(this::calcRating);
		return works;
	}

	public Work findById(Long id) {
		Optional<Work> work = workRepository.findById(id);
		if (work.isEmpty()) {
			throw new EntityNotFoundException(Work.class, id);
		}
		Work result = work.get();
		calcRating(result);
		return result;
	}

	@Transactional
	public Work create(WorkResponseDTO workResponseDTO) {
		validation(workResponseDTO);
		User user = userService.findById(Long.valueOf(workResponseDTO.getAuthor()));
		Work work = Work.builder()
				.author(user)
				.title(workResponseDTO.getTitle())
				.fileUrl(workResponseDTO.getFileUrl())
				.description(workResponseDTO.getDescription())
				.build();
		work = workRepository.save(work);
		return findById(work.getId());
	}

	@Transactional
	public Work update(WorkResponseDTO workResponseDTO) {
		validation(workResponseDTO);
		User user = userService.findById(Long.valueOf(workResponseDTO.getAuthor()));
		Work work = findById(Long.valueOf(workResponseDTO.getId()));
		work.setAuthor(user);
		work.setTitle(workResponseDTO.getTitle());
		work.setDescription(workResponseDTO.getDescription());
		work = workRepository.save(work);
		return findById(work.getId());
	}

	@Transactional
	public void delete(Long id) {
		workRepository.deleteById(id);
	}

	private void calcRating(Work work) {
		work.setRating(ratingService.findRatingByWorkId(work.getId()));
	}

	private void validation(WorkResponseDTO workResponseDTO) {
		Set<ConstraintViolation<WorkResponseDTO>> violations = workDTOValidator.validate(workResponseDTO);
		if (!violations.isEmpty()) {
			String errorMessage = workDTOValidator.getValidationErrorMessage(violations);
			throw new ValidationException(errorMessage);
		}
	}
}
