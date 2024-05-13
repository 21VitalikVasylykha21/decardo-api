package org.decardo.user;

import org.decardo.art.ArtMapper;
import org.decardo.art.ArtService;
import org.decardo.watchlist.WatchList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2023/11/12
 */

@Service
public class UserMapper {

	@Autowired
	private ArtMapper artMapper;

	@Autowired
	private ArtService artService;

	public UserDTO convert(User user) {
		fillArt(user);
		boolean isMyProfile = SecurityContextHolder.getContext().getAuthentication().getName().equals(user.getUsername());
		return UserDTO.builder()
				.role(user.getRole().name().toLowerCase())
				.id(String.valueOf(user.getId()))
				.description(user.getDetails())
				.bannerImage(user.getBanner())
				.username(user.getUsername())
				.contact(user.getContact())
				.isMyProfile(isMyProfile)
				.avatar(user.getAvatar())
				.email(user.getEmail())
				.arts(user.getArts().stream()
						.map(artMapper::convert)
						.toList())
				.wishlist(user.getWatchlist().stream()
						.map(WatchList::getArt)
						.map(artMapper::convert)
						.toList())
				.build();
	}

	private void fillArt(User user) {
		user.getArts().forEach(art -> artService.calcRating(art));
		user.getWatchlist().stream().map(WatchList::getArt).forEach(art -> artService.calcRating(art));
	}
}