package org.decardo.art;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.decardo.art.model.Model;
import org.decardo.art.model.ModelMapper;
import org.decardo.art.model.ModelRepository;
import org.decardo.exception.EntityNotFoundException;
import org.decardo.rating.RatingService;
import org.decardo.user.User;
import org.decardo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/03/24
 */
@Service
public class ArtService {
	@Autowired
	private ArtRepository artRepository;

	@Autowired
	private ModelRepository modelRepository;

	@Autowired
	private RatingService ratingService;

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ArtDTOValidator artDTOValidator;

	public List<Art> findAll() {
		List<Art> arts = artRepository.findAll();
		arts.forEach(this::calcRating);
		return arts;
	}

	public ArtSearchResponse search(String title, List<String> tags, Integer page, Integer limit) {
		String[] tagsArray = null;
		if (tags != null) {
			tagsArray = tags.toArray(new String[0]);
		}
		Page<Art> artPage = artRepository.findByTitleAndTags(title, tagsArray, PageRequest.of(page - 1, limit));
		artPage.forEach(this::calcRating);
		return new ArtSearchResponse(artPage.getTotalElements(), artPage.getContent());
	}

	public Art findById(Long id) {
		Optional<Art> art = artRepository.findById(id);
		if (art.isEmpty()) {
			throw new EntityNotFoundException(Art.class, id);
		}
		Art result = art.get();
		calcRating(result);
		return result;
	}

	@Transactional
	public Art create(ArtInputDTO artInputDTO) {
		artDTOValidator.validate(artInputDTO);
		Model model = modelMapper.convert(artInputDTO);
		model = modelRepository.save(model);
		User user = userService.findByUsername(artInputDTO.getOwner());
		Art art = Art.builder()
				.author(user)
				.model(model)
				.tags(artInputDTO.getTags())
				.title(artInputDTO.getTitle())
				.description(artInputDTO.getDescription())
				.build();
		art = artRepository.save(art);
		return findById(art.getId());
	}

	@Transactional
	public Art update(ArtInputDTO artInputDTO) {
		artDTOValidator.validate(artInputDTO);
		User user = userService.findById(Long.valueOf(artInputDTO.getOwner()));
		Art art = findById(Long.valueOf(artInputDTO.getId()));
		Model model = modelMapper.convert(artInputDTO);
		model.setId(art.getModel().getId());
		model = modelRepository.save(model);
		art.setAuthor(user);
		art.setTitle(artInputDTO.getTitle());
		art.setDescription(artInputDTO.getDescription());
		art.setTags(artInputDTO.getTags());
		art.setModel(model);
		art = artRepository.save(art);
		return findById(art.getId());
	}

	@Transactional
	public void delete(Long id) {
		artRepository.deleteById(id);
	}

	public void calcRating(Art art) {
		art.setRating(ratingService.findRatingByArtId(art.getId()));
	}
}
