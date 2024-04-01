package org.decardo.watchlist;

import org.decardo.user.UserMapper;
import org.decardo.worker.WorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/04/01
 */

@Service
public class WatchListMapper {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private WorkMapper workMapper;

	public WatchListDTO convert(WatchList watchList) {
		return WatchListDTO.builder()
				.work(workMapper.convert(watchList.getWork()))
				.user(userMapper.convert(watchList.getUser()))
				.build();
	}

	public WatchListWithoutUserDTO convertWithoutUser(WatchList watchList) {
		return WatchListWithoutUserDTO.builder()
				.work(workMapper.convert(watchList.getWork()))
				.build();
	}
}
