package org.decardo.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.decardo.art.Art;
import org.decardo.watchlist.WatchList;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`USER`")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 3, max = 20)
	private String username;

	@NotBlank
	@Size(min = 6, max = 255)
	private String password;

	@Email
	@Size(max = 50)
	private String email;

	@Builder.Default
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	private List<WatchList> watchlist = Collections.emptyList();

	@Builder.Default
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "author_id", referencedColumnName = "id", insertable = false, updatable = false)
	private List<Art> arts = Collections.emptyList();

	@Enumerated(EnumType.STRING)
	@Builder.Default
	private UserRole role = UserRole.USER;
	private String details;
	private String contact;
	private String avatar;
	private String banner;
}
