package org.decardo.worker;

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
import jakarta.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.decardo.comment.Comment;
import org.decardo.user.User;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/03/24
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`WORK`")
public class Work {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@Column(name = "FILE_URL")
	private String fileUrl;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHOR_ID")
	private User author;

	@Builder.Default
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "work_id", referencedColumnName = "id", insertable = false, updatable = false)
	private List<Comment> comments = Collections.emptyList();

	@Transient
	private Double rating;
}
