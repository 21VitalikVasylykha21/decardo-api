package org.decardo.worker;

import java.util.List;
import java.util.Optional;
import org.decardo.exception.EntityNotFoundException;
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

	public List<Work> findAll() {
		return workRepository.findAll();
	}

	public Work findById(Long id) {
		Optional<Work> work = workRepository.findById(id);
		if (work.isEmpty()) {
			throw new EntityNotFoundException(Work.class, id);
		}
		return work.get();
	}
}
