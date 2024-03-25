package org.decardo.worker;

import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/03/24
 */
@Service
public class WorkMapper {

	public WorkDTO workToWorkDTO(Work worker) {
		return WorkDTO.builder()
				.id(worker.getId())
				.title(worker.getTitle())
				.fileUrl(worker.getFileUrl())
				.description(worker.getDescription())
				.build();
	}

}