package com.khun.movievault.controller;

import com.khun.movievault.dto.user.*;
import com.khun.movievault.exception.UserAlreadyExistException;
import com.khun.movievault.exception.UserInvalidCredentialException;
import com.khun.movievault.exception.UserNotFoundException;
import com.khun.movievault.jwtconfig.JWTMgmtUtilityService;
import com.khun.movievault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movievault/v1/api/auth")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private JWTMgmtUtilityService jwtMgmtUtilityService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/addUser")
    public ResponseEntity<UserProfileResponse> saveUser(@RequestBody UserRequest userRequest) throws UserAlreadyExistException {
        return new ResponseEntity(userService.saveUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthResponse> login(@RequestBody UserAuthRequest userAuthRequest) throws UserNotFoundException, UserInvalidCredentialException {
        UserAuthResponse userAuthResponse = null;
        try {
            var email = userAuthRequest.email();
            var password = userAuthRequest.password();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,
                            password)
            );
            var jwtToken = jwtMgmtUtilityService.generateToken(email);
            var user = userService.getUserByEmail(email);
            if (user != null) {
                userAuthResponse = new UserAuthResponse(user.getUserId(), user.getEmail(), jwtToken);
            }
        } catch (Exception ex) {
            System.out.println(String.format("User Authentication Exception is: %s", ex));
            throw ex;
        }
        return ResponseEntity.ok(userAuthResponse);
    }
}
