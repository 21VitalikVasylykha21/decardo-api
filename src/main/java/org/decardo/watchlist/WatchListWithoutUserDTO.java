package org.decardo.watchlist;

import lombok.Builder;
import lombok.Getter;
import org.decardo.art.ArtResponseDTO;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/04/01
 */
@Getter
@Builder
public class WatchListWithoutUserDTO {
	public ArtResponseDTO art;
}
