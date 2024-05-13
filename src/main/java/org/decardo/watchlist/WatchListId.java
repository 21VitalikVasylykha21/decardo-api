package org.decardo.watchlist;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/04/01
 */

@Builder
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class WatchListId implements Serializable {

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "art_id")
	private Long artId;
}