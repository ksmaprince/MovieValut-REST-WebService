package com.khun.movievault.service;

import com.khun.movievault.dto.user.UserProfileResponse;
import com.khun.movievault.dto.user.UserRequest;
import com.khun.movievault.exception.UserAlreadyExistException;
import com.khun.movievault.exception.NotFoundException;
import com.khun.movievault.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();

    User getUserByEmail(String email) throws NotFoundException;

    UserProfileResponse saveUser(UserRequest userRequest) throws UserAlreadyExistException, NotFoundException;

    UserProfileResponse updatePassword(Long userId, String password);

}
