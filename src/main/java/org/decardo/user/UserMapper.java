package org.decardo.user;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2023/11/12
 */

public interface UserMapper {

	UserDTO userToUserDTO(User user);

	User userDTOToUser(UserDTO userDTO);
}