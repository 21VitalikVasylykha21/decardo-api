package org.decardo.watchlist;

import lombok.Builder;
import lombok.Getter;
import org.decardo.user.UserDTO;
import org.decardo.worker.WorkDTO;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/04/01
 */
@Getter
@Builder
public class WatchListWithoutUserDTO {
	public WorkDTO work;
}
