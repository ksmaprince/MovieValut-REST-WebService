package com.khun.movievault.service;

import com.khun.movievault.data.User;
import com.khun.movievault.dto.user.UserProfileResponse;
import com.khun.movievault.dto.user.UserRequest;
import com.khun.movievault.dto.user.UserResponse;
import com.khun.movievault.exception.UserDuplicateException;
import com.khun.movievault.exception.UserInvalidCredentialException;
import com.khun.movievault.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();

    UserResponse loginByUserNameAndPassword(String email, String password) throws UserNotFoundException, UserInvalidCredentialException;

    UserProfileResponse saveUser(UserRequest userRequest) throws UserDuplicateException;

    UserProfileResponse updatePassword(Long userId, String password);

}
