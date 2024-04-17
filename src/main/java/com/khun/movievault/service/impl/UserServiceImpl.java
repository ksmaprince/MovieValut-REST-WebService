package com.khun.movievault.service.impl;

import com.khun.movievault.dto.profile.ProfileResponse;
import com.khun.movievault.dto.user.UserProfileResponse;
import com.khun.movievault.dto.user.UserRequest;
import com.khun.movievault.exception.UserAlreadyExistException;
import com.khun.movievault.exception.UserNotFoundException;
import com.khun.movievault.model.Profile;
import com.khun.movievault.model.Role;
import com.khun.movievault.model.User;
import com.khun.movievault.repository.RoleRepository;
import com.khun.movievault.repository.UserRepository;
import com.khun.movievault.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(String.format("User with email %s is not found", email)));
    }

    @Override
    public UserProfileResponse saveUser(UserRequest userRequest) throws UserAlreadyExistException {
        val existingUser = userRepository.findByEmail(userRequest.email());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException(String.format("User with EmailID: %s is already exist", userRequest.email()));
        }
        List<Role> roles = new ArrayList<>();
        userRequest.roleIds().forEach(roleId -> roleRepository.findById(roleId).ifPresent(role -> roles.add(role)));
        val encodedPassword = new BCryptPasswordEncoder().encode(userRequest.password());
        val user = userRepository.save(new User(0L, userRequest.email(), encodedPassword, true, true, true, true, new Profile(0L, userRequest.profile().fullName(), userRequest.profile().contactNo(), userRequest.profile().imageUrl(), null), roles));

        return new UserProfileResponse(user.getUserId(), user.getEmail(), "************", new ProfileResponse(user.getProfile().getProfileId(), user.getProfile().getFullName(), user.getProfile().getContactNo(), user.getProfile().getImageUrl()));
    }

    @Override
    public UserProfileResponse updatePassword(Long userId, String password) {
        val userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            val u = userOptional.get();
            u.setPassword(password);
            val user = userRepository.save(u);
            return new UserProfileResponse(user.getUserId(), user.getEmail(), user.getPassword(), new ProfileResponse(user.getProfile().getProfileId(), user.getProfile().getFullName(), user.getProfile().getContactNo(), user.getProfile().getImageUrl()));
        } else {
            return null;
        }
    }
}
