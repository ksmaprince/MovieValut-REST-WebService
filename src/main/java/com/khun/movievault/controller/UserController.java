package com.khun.movievault.controller;

import com.khun.movievault.dto.user.*;
import com.khun.movievault.exception.CurrentPasswordNotMatchException;
import com.khun.movievault.exception.NotFoundException;
import com.khun.movievault.exception.UserAlreadyExistException;
import com.khun.movievault.exception.UserInvalidCredentialException;
import com.khun.movievault.jwtconfig.JWTMgmtUtilityService;
import com.khun.movievault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public ResponseEntity<UserProfileResponse> saveUser(@RequestBody UserRequest userRequest) throws UserAlreadyExistException, NotFoundException {
        return new ResponseEntity(userService.saveUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthResponse> login(@RequestBody UserAuthRequest userAuthRequest) throws NotFoundException, UserInvalidCredentialException {
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
                userAuthResponse = new UserAuthResponse(user.getProfile().getProfileId(), user.getEmail(), jwtToken);
            } else {
                throw new UserInvalidCredentialException("Invalid Credential");
            }
        } catch (Exception ex) {
            System.out.println(String.format("User Authentication Exception is: %s", ex));
            throw new UserInvalidCredentialException(ex.getMessage());
        }
        return ResponseEntity.ok(userAuthResponse);
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<UpdatePasswordResponse> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) throws CurrentPasswordNotMatchException, NotFoundException {
        UpdatePasswordResponse updatePasswordResponse = null;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(updatePasswordRequest.email(),
                            updatePasswordRequest.currentPassword())
            );
            var user = userService.getUserByEmail(updatePasswordRequest.email());
            if (user != null) {
                updatePasswordResponse = userService.updatePassword(user.getUserId(), updatePasswordRequest.newPassword());
            } else {
                throw new UserInvalidCredentialException("Invalid Credential");
            }
        } catch (Exception ex) {
            throw new CurrentPasswordNotMatchException("Invalid Current Password");
        }

        return ResponseEntity.ok(updatePasswordResponse);
    }
}
