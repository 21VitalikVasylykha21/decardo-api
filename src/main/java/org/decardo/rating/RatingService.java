package org.decardo.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/04/02
 */
@Service
public class RatingService {
	@Autowired
	private RatingRepository ratingRepository;

	public Double findRatingByWorkId(Long workId) {
		Double rating = ratingRepository.getAverageRatingByWorkId(workId);
		return rating == null ? 0D : rating;
	}
}
