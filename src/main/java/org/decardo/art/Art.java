package org.decardo.art;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.decardo.art.model.Model;
import org.decardo.comment.Comment;
import org.decardo.user.User;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/03/24
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`ART`")
public class Art {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;

	@Column(name = "TAG")
	private List<String> tags;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AUTHOR_ID")
	private User author;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MODEL_ID")
	private Model model;

	@Builder.Default
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "art_id", referencedColumnName = "id", insertable = false, updatable = false)
	private List<Comment> comments = Collections.emptyList();

	@Transient
	private Double rating;
}
