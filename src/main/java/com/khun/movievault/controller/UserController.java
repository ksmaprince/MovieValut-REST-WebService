package com.khun.movievault.controller;

import com.khun.movievault.dto.user.LoginRequest;
import com.khun.movievault.dto.user.UserProfileResponse;
import com.khun.movievault.dto.user.UserRequest;
import com.khun.movievault.dto.user.UserResponse;
import com.khun.movievault.exception.UserDuplicateException;
import com.khun.movievault.exception.UserInvalidCredentialException;
import com.khun.movievault.exception.UserNotFoundException;
import com.khun.movievault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movievault/v1/api")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserProfileResponse> saveUser(@RequestBody UserRequest userRequest) throws UserDuplicateException {
        return new ResponseEntity(userService.saveUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) throws UserNotFoundException, UserInvalidCredentialException {
        return new ResponseEntity<>(userService.loginByUserNameAndPassword(loginRequest.email(), loginRequest.password()), HttpStatus.ACCEPTED);
    }
}
