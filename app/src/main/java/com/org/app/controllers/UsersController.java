package com.org.app.controllers;

import com.org.app.services.impl.UserServiceImpl;
import com.org.app.dtos.UserDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserServiceImpl userService;

    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<HttpStatus> create(@RequestBody UserDto user) {
        logger.info("Creating user");

        UserDto userDto = userService.create(user);
        logger.info("User created id: " + userDto.getId());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping(value ="/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto userDto = userService.getById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(value ="/users")
    public ResponseEntity<List<UserDto>> getAll() {
        logger.info("Getting all users");
        List<UserDto> userDtoList = userService.getAll();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @PutMapping(value ="/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                              @RequestBody @Valid UserDto userDto) {
        userDto.setId(userId);
        UserDto updatedUser = userService.update(userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteById(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
}
