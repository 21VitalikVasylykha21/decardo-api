package org.decardo.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2023/11/12
 */
@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDTO userToUserDTO(User user);

	User userDTOToUser(UserDTO userDTO);
}