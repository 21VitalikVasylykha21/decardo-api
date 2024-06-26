package org.decardo.watchlist;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.decardo.user.User;
import org.decardo.art.Art;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/04/01
 */

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`WATCHLIST`")
public class WatchList {

	@EmbeddedId
	private WatchListId id;

	@NotNull
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;

	@NotNull
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "art_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Art art;
}

