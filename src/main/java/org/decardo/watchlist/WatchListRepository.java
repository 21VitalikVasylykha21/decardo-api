package org.decardo.watchlist;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/04/01
 */
public interface WatchListRepository extends JpaRepository<WatchList, WatchListId> {
	List<WatchList> findByUserId(Long userId);

	Optional<WatchList> findByUserIdAndArtId(Long userId, Long artId);

	void deleteByUserIdAndArtId(Long userId, Long artId);
}
