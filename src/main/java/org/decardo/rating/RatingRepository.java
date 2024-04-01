package org.decardo.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/04/02
 */
public interface RatingRepository extends JpaRepository<Rating, RatingId> {
	@Query(value = "SELECT AVG(rating) FROM \"RATING\" WHERE WORK_ID = :workId", nativeQuery = true)
	Double getAverageRatingByWorkId(Long workId);
}
