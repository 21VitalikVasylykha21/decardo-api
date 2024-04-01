package org.decardo.rating;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/04/01
 */

@Builder
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RatingId implements Serializable {

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "work_id")
	private Long workId;
}