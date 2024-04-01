package org.decardo.watchlist;

import lombok.Builder;
import lombok.Getter;
import org.decardo.user.UserDTO;
import org.decardo.worker.WorkResponseDTO;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/04/01
 */
@Getter
@Builder
public class WatchListDTO {
	public UserDTO user;
	public WorkResponseDTO work;
}
