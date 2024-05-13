package org.decardo.art;

import java.util.Collections;
import java.util.List;
import org.decardo.comment.CommentDTO;
import org.decardo.comment.CommentMapper;
import org.decardo.art.model.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/03/24
 */
@Service
public class ArtMapper {
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ModelMapper modelMapper;

	public ArtResponseDTO convert(Art worker) {
		List<CommentDTO> comments = Collections.emptyList();
		if (worker.getComments() != null) {
			comments = worker.getComments().stream()
					.map(commentMapper::convert)
					.toList();

		}
		return ArtResponseDTO.builder()
				.comments(comments)
				.tags(worker.getTags())
				.title(worker.getTitle())
				.id(worker.getId().toString())
				.model(worker.getModel().getFile())
				.description(worker.getDescription())
				.rating(worker.getRating().toString())
				.preview(worker.getModel().getPreview())
				.owner(worker.getAuthor().getUsername())
				.format(worker.getModel().getFormat().name())
				.settings(modelMapper.convert(worker.getModel()))
				.build();
	}

}