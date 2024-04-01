package org.decardo.worker;

import java.util.List;
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

	public Work findById(Long userId) {
		List<Work> works = workRepository.findAllById(List.of(userId));
		if (works.size() != 1) {
			throw new EntityNotFoundException(Work.class, userId);
		}
		return works.get(0);
	}
}
