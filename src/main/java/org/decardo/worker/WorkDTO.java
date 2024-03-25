package org.decardo.worker;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.decardo.user.UserDTO;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/03/24
 */
@Getter
@Setter
@Builder
public class WorkDTO {
	private Long id;

	private String title;

	private String description;

	private String fileUrl;

	private UserDTO author;
}
