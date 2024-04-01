package org.decardo.watchlist;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.decardo.exception.ExistObjectException;
import org.decardo.user.User;
import org.decardo.user.UserService;
import org.decardo.worker.Work;
import org.decardo.worker.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/04/01
 */
@Service
public class WatchListService {
	@Autowired
	private WatchListRepository watchListRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private WorkService workService;

	public List<WatchList> findAll() {
		return watchListRepository.findAll();
	}

	public List<WatchList> findByUserId(Long userId) {
		return watchListRepository.findByUserId(userId);
	}

	@Transactional
	public void delete(Long userId, Long workId) {
		watchListRepository.deleteByUserIdAndWorkId(userId, workId);
	}

	@Transactional
	public WatchList save(Long userId, Long workId) {
		Optional<WatchList> watchListExist = watchListRepository.findByUserIdAndWorkId(userId, workId);
		if (watchListExist.isPresent()) {
			throw new ExistObjectException(WatchList.class);
		}
		User user = userService.findById(userId);
		Work work = workService.findById(workId);
		WatchList watchList = WatchList.builder()
				.id(WatchListId.builder()
						.userId(userId)
						.workId(workId)
						.build())
				.user(user)
				.work(work)
				.build();
		return watchListRepository.save(watchList);
	}
}
