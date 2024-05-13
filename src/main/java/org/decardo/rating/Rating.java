package org.decardo.rating;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.decardo.util.DateUtils;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/04/02
 */

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`RATING`")
public class Rating {
	@EmbeddedId
	public RatingId id;

	@NotNull
	public Integer rating;

	@Column(name = "create_date")
	@Builder.Default
	private Timestamp createDate = DateUtils.nowTimestamp();
}
