package org.decardo.watchlist;

import lombok.Builder;
import lombok.Getter;
import org.decardo.art.ArtResponseDTO;
import org.decardo.user.UserDTO;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/04/01
 */
@Getter
@Builder
public class WatchListDTO {
	public UserDTO user;
	public ArtResponseDTO art;
}
