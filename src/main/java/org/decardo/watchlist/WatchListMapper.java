package org.decardo.watchlist;

import org.decardo.user.UserMapper;
import org.decardo.art.ArtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/04/01
 */

@Service
public class WatchListMapper {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ArtMapper artMapper;

	public WatchListDTO convert(WatchList watchList) {
		return WatchListDTO.builder()
				.art(artMapper.convert(watchList.getArt()))
				.user(userMapper.convert(watchList.getUser()))
				.build();
	}

	public WatchListWithoutUserDTO convertWithoutUser(WatchList watchList) {
		return WatchListWithoutUserDTO.builder()
				.art(artMapper.convert(watchList.getArt()))
				.build();
	}
}
