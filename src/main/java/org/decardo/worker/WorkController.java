package org.decardo.worker;

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
 * @since 2024/03/24
 */

@CrossOrigin
@RestController
@RequestMapping("api/v1/work")
public class WorkController {
	@Autowired
	private WorkService workService;
	@Autowired
	private WorkMapper workMapper;

	@GetMapping
	public ListObjectResponse<WorkResponseDTO> getAll() {
		return new ListObjectResponse<>(workService.findAll().stream()
				.map(workMapper::convert)
				.toList());
	}

	@GetMapping("/{id}")
	public CustomResponse findById(@PathVariable Long id) {
		try {
			return new ListObjectResponse<>(Stream.of(workService.findById(id))
					.map(workMapper::convert)
					.toList());
		} catch (Exception e) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping
	public CustomResponse create(@RequestBody WorkResponseDTO workResponseDTO) {
		try {
			Work work = workService.create(workResponseDTO);
			return new ListObjectResponse<>(Stream.of(work)
					.map(workMapper::convert)
					.toList());
		} catch (Exception e) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping
	public CustomResponse update(@RequestBody WorkResponseDTO workResponseDTO) {
		try {
			Work work = workService.update(workResponseDTO);
			return new ListObjectResponse<>(Stream.of(work)
					.map(workMapper::convert)
					.toList());
		} catch (Exception e) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public MessageResponse create(@PathVariable Long id) {
		workService.delete(id);
		return new MessageResponse<>("Comment successfully removed");
	}
}
