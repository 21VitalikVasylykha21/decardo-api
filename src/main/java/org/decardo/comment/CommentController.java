package org.decardo.comment;

import java.util.List;
import java.util.stream.Stream;
import org.decardo.response.CustomResponse;
import org.decardo.response.ListObjectResponse;
import org.decardo.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/04/02
 */

@CrossOrigin
@RestController
@RequestMapping("api/v1/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private CommentMapper commentMapper;

	@GetMapping
	public ListObjectResponse<CommentDTO> findAll() {
		List<Comment> comments = commentService.findAll();
		return new ListObjectResponse<>(comments.stream()
				.map(commentMapper::convert)
				.toList());
	}

	@GetMapping("/user/{userId}")
	public ListObjectResponse<CommentDTO> findByUserId(@PathVariable Long userId) {
		List<Comment> comments = commentService.findByUserId(userId);
		return new ListObjectResponse<>(comments.stream()
				.map(commentMapper::convert)
				.toList());
	}

	@GetMapping("/{id}")
	public CustomResponse findById(@PathVariable Long id) {
		try {
			Comment comment = commentService.findById(id);
			return new ListObjectResponse<>(Stream.of(comment)
					.map(commentMapper::convert)
					.toList());
		} catch (Exception e) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/work/{workId}")
	public ListObjectResponse<CommentDTO> findByWorkId(@PathVariable Long workId) {
		List<Comment> comments = commentService.findByWorkId(workId);
		return new ListObjectResponse<>(comments.stream()
				.map(commentMapper::convert)
				.toList());
	}

	@PostMapping
	public CustomResponse create(@RequestBody CommentDTO commentDTO) {
		try {
			Comment comment = commentService.create(commentDTO);
			return new ListObjectResponse<>(Stream.of(comment)
					.map(commentMapper::convert)
					.toList());
		} catch (Exception e) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping
	public CustomResponse update(@RequestBody CommentDTO commentDTO) {
		try {
			Comment comment = commentService.update(commentDTO);
			return new ListObjectResponse<>(Stream.of(comment)
					.map(commentMapper::convert)
					.toList());
		} catch (Exception e) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public MessageResponse create(@PathVariable Long id) {
		commentService.delete(id);
		return new MessageResponse<>("Comment successfully removed");
	}
}
