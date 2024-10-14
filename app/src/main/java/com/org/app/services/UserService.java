package com.org.app.services;

import com.org.app.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);
    List<UserDto> getAll();

    UserDto getById(long id);
    void deleteById(long id);

    UserDto update(UserDto userDto);
}
