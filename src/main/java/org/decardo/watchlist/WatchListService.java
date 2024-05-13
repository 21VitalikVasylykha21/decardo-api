package org.decardo.watchlist;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.decardo.art.Art;
import org.decardo.exception.ExistObjectException;
import org.decardo.user.User;
import org.decardo.user.UserService;
import org.decardo.art.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/04/01
 */
@Service
public class WatchListService {
	@Autowired
	private WatchListRepository watchListRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ArtService artService;

	public List<WatchList> findAll() {
		return watchListRepository.findAll();
	}

	public List<WatchList> findByUserId(Long userId) {
		return watchListRepository.findByUserId(userId);
	}

	@Transactional
	public void delete(Long userId, Long artId) {
		watchListRepository.deleteByUserIdAndArtId(userId, artId);
	}

	@Transactional
	public WatchList save(Long userId, Long artID) {
		Optional<WatchList> watchListExist = watchListRepository.findByUserIdAndArtId(userId, artID);
		if (watchListExist.isPresent()) {
			throw new ExistObjectException(WatchList.class);
		}
		User user = userService.findById(userId);
		Art art = artService.findById(artID);
		WatchList watchList = WatchList.builder()
				.id(WatchListId.builder()
						.userId(userId)
						.artId(artID)
						.build())
				.user(user)
				.art(art)
				.build();
		return watchListRepository.save(watchList);
	}
}
