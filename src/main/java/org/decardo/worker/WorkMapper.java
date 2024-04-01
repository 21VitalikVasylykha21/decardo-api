package org.decardo.worker;

import java.util.Collections;
import java.util.List;
import org.decardo.comment.CommentDTO;
import org.decardo.comment.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/03/24
 */
@Service
public class WorkMapper {
	@Autowired
	private CommentMapper commentMapper;

	public WorkResponseDTO convert(Work worker) {
		List<CommentDTO> comments = Collections.emptyList();
		if (worker.getComments() != null) {
			comments = worker.getComments().stream()
					.map(commentMapper::convert)
					.toList();

		}
		return WorkResponseDTO.builder()
				.comments(comments)
				.title(worker.getTitle())
				.fileUrl(worker.getFileUrl())
				.id(worker.getId().toString())
				.description(worker.getDescription())
				.rating(worker.getRating().toString())
				.author(worker.getAuthor().getId().toString())
				.build();
	}

}