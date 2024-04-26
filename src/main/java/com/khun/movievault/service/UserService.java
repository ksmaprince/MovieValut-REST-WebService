package com.khun.movievault.service;

import com.khun.movievault.dto.user.UpdatePasswordResponse;
import com.khun.movievault.dto.user.UserProfileResponse;
import com.khun.movievault.dto.user.UserRequest;
import com.khun.movievault.exception.CurrentPasswordNotMatchException;
import com.khun.movievault.exception.UserAlreadyExistException;
import com.khun.movievault.exception.NotFoundException;
import com.khun.movievault.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getUserByEmail(String email) throws NotFoundException;

    UserProfileResponse saveUser(UserRequest userRequest) throws UserAlreadyExistException, NotFoundException;

    UpdatePasswordResponse updatePassword(Long userId, String newPassword) throws NotFoundException, CurrentPasswordNotMatchException;

}
