package org.decardo.watchlist;

import java.util.List;
import java.util.stream.Stream;
import org.decardo.response.CustomResponse;
import org.decardo.response.ListObjectResponse;
import org.decardo.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/04/01
 */

@CrossOrigin
@RestController
@RequestMapping("api/v1/favorite")
public class WatchListController {
	@Autowired
	private WatchListService watchListService;

	@Autowired
	private WatchListMapper watchListMapper;

	@GetMapping
	public ListObjectResponse<WatchListDTO> findAll() {
		List<WatchList> watchLists = watchListService.findAll();
		return new ListObjectResponse<>(watchLists.stream()
				.map(watchListMapper::convert)
				.toList());
	}

	@GetMapping("/user/{userId}")
	public ListObjectResponse<WatchListWithoutUserDTO> findByUserId(@PathVariable Long userId) {
		List<WatchList> watchLists = watchListService.findByUserId(userId);
		return new ListObjectResponse<>(watchLists.stream()
				.map(watchListMapper::convertWithoutUser)
				.toList());
	}

	@PostMapping("/user/{userId}/art/{artId}")
	public CustomResponse create(@PathVariable Long userId,
								 @PathVariable Long artId) {
		try {
			WatchList watchList = watchListService.save(userId, artId);
			return new ListObjectResponse<>(Stream.of(watchList)
					.map(watchListMapper::convertWithoutUser)
					.toList());
		} catch (Exception e) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("/user/{userId}/art/{artId}")
	public MessageResponse delete(@PathVariable Long userId,
								  @PathVariable Long artId) {
		watchListService.delete(userId, artId);
		return new MessageResponse<>("Reference successfully removed");
	}
}
