package com.org.app.services.impl;

import com.org.app.database.entities.User;
import com.org.app.database.repositories.UserRepository;
import com.org.app.dtos.UserDto;
import com.org.app.exception.ResourceInvalidException;
import com.org.app.exception.ResourceNotFoundException;
import com.org.app.mappers.UserMapper;
import com.org.app.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto create(UserDto userDto) {
        if(!Objects.isNull(userDto.getId())){
            throw new ResourceInvalidException("Not accepting id in create");
        }

        User user = UserMapper.INSTANCE.convertDtoToEntity(userDto);
        user = this.userRepository.save(user);
        return UserMapper.INSTANCE.convertEntityToDto(user);

    }

    @Override
    public List<UserDto> getAll() {
        List<User> userList = this.userRepository.findAll();
        return userList.stream().map(UserMapper.INSTANCE::convertEntityToDto)
                .collect(Collectors.toList());

    }

    @Override
    public UserDto getById(long id) {
        User user = this.userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return UserMapper.INSTANCE.convertEntityToDto(user);
    }

    @Override
    public void deleteById(long id) {
        logger.debug("Delete user by id: " + id);
        this.userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        logger.debug("User deleted");
        userRepository.deleteById(id);
    }

    @Override
    public UserDto update(UserDto userDto) {
        logger.debug("Update user by id: " + userDto.getId());
        User user = this.userRepository.findById(userDto.getId()).
                orElseThrow(() -> new ResourceNotFoundException("User", "id", userDto.getId()));
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setUsername(userDto.getUsername());

        logger.debug("User updated");
        user = this.userRepository.save(user);
        return UserMapper.INSTANCE.convertEntityToDto(user);
    }
}
