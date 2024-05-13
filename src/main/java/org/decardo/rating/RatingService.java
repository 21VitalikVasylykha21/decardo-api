package org.decardo.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/04/02
 */
@Service
public class RatingService {
	@Autowired
	private RatingRepository ratingRepository;

	public Double findRatingByArtId(Long artId) {
		Double rating = ratingRepository.getAverageRatingByArtId(artId);
		return rating == null ? 0D : rating;
	}
}
