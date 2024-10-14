package com.org.app.mappers.impl;


import com.org.app.database.entities.User;
import com.org.app.dtos.UserDto;
import com.org.app.mappers.UserMapper;


public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto convertEntityToDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();

        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());

        return userDto;
    }

    @Override
    public User convertDtoToEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());

        return user;
    }
}
