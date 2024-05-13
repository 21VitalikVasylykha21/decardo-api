package org.decardo.art;

import java.util.List;
import java.util.stream.Stream;
import org.decardo.response.CustomResponse;
import org.decardo.response.ListObjectResponse;
import org.decardo.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/03/24
 */

@CrossOrigin
@RestController
@RequestMapping("api/v1/arts")
public class ArtController {
	@Autowired
	private ArtService artService;
	@Autowired
	private ArtMapper artMapper;

	@GetMapping
	public ListObjectResponse<ArtResponseDTO> find(@RequestParam(name = "query", required = false) String title,
												   @RequestParam(name = "tags", required = false) List<String> tags,
												   @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
												   @RequestParam(name = "limit", defaultValue = "20", required = false) Integer limit) {
		ArtSearchResponse artSearchResponse = artService.search(title, tags, page, limit);
		return new ListObjectResponse<>(artSearchResponse.total(),
				artSearchResponse.arts().stream()
						.map(artMapper::convert)
						.toList());
	}

	@GetMapping("/{id}")
	public CustomResponse findById(@PathVariable Long id) {
		try {
			return new ListObjectResponse<>(Stream.of(artService.findById(id))
					.map(artMapper::convert)
					.toList());
		} catch (Exception e) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public CustomResponse create(@ModelAttribute ArtInputDTO artInputDTO) {
		try {
			Art art = artService.create(artInputDTO);
			return new ListObjectResponse<>(Stream.of(art)
					.map(artMapper::convert)
					.toList());
		} catch (Exception e) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public CustomResponse update(@ModelAttribute ArtInputDTO artInputDTO) {
		try {
			Art art = artService.update(artInputDTO);
			return new ListObjectResponse<>(Stream.of(art)
					.map(artMapper::convert)
					.toList());
		} catch (Exception e) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public CustomResponse delete(@PathVariable Long id) {
		artService.delete(id);
		return new MessageResponse<>("Art successfully removed");
	}
}
