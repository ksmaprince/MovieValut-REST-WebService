package com.khun.movievault.service.impl;

import com.khun.movievault.data.Profile;
import com.khun.movievault.data.User;
import com.khun.movievault.dto.profile.ProfileResponse;
import com.khun.movievault.dto.user.UserProfileResponse;
import com.khun.movievault.dto.user.UserRequest;
import com.khun.movievault.dto.user.UserResponse;
import com.khun.movievault.exception.UserDuplicateException;
import com.khun.movievault.exception.UserInvalidCredentialException;
import com.khun.movievault.exception.UserNotFoundException;
import com.khun.movievault.repository.UserRepository;
import com.khun.movievault.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse loginByUserNameAndPassword(String email, String password) throws UserNotFoundException, UserInvalidCredentialException {
        User user = userRepository.getUserByEmailPassword(email).orElse(null);
        if (user!=null){
            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                return new UserResponse(
                        user.getUserId(),
                        user.getEmail(),
                        user.getPassword(),
                        "Token xxxxxxxx",
                        "Refresh Token xxxxxxxxxxxxx"
                );
            }else {
                throw new UserInvalidCredentialException("Invalid Email and Password.");
            }
        }else {
            throw new UserNotFoundException(String.format("User with email %s is not found.", email));
        }
    }

    @Override
    public UserProfileResponse saveUser(UserRequest userRequest) throws UserDuplicateException {
        val emails = userRepository.findAll().stream().filter(user -> user.getEmail().equals(userRequest.email()));
        if (emails.count()>=1){
            throw new UserDuplicateException(String.format("User with EmailID: %s is already exist", userRequest.email()));
        }
        val user = userRepository.save(new User(
                0L,
                userRequest.email(),
                userRequest.password(),
                new Profile(
                        0L,
                        userRequest.profile().fullName(),
                        userRequest.profile().contactNo(),
                        userRequest.profile().imageUrl(),
                        null
                )));

        return new UserProfileResponse(
                user.getUserId(),
                user.getEmail(),
                user.getPassword(),
                new ProfileResponse(
                        user.getProfile().getProfileId(),
                        user.getProfile().getFullName(),
                        user.getProfile().getContactNo(),
                        user.getProfile().getImageUrl()
                )
        );
    }

    @Override
    public UserProfileResponse updatePassword(Long userId, String password) {
        val userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            val u = userOptional.get();
            u.setPassword(password);
            val user = userRepository.save(u);
            return new UserProfileResponse(
                    user.getUserId(),
                    user.getEmail(),
                    user.getPassword(),
                    new ProfileResponse(
                            user.getProfile().getProfileId(),
                            user.getProfile().getFullName(),
                            user.getProfile().getContactNo(),
                            user.getProfile().getImageUrl()
                    )
            );
        } else {
            return null;
        }
    }
}
