package com.org.app.mappers;


import com.org.app.database.entities.User;
import com.org.app.dtos.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto convertEntityToDto(User user);

    User convertDtoToEntity(UserDto userDto);
}
