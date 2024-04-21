package com.khun.movievault.service.impl;

import com.khun.movievault.dto.profile.ProfileResponse;
import com.khun.movievault.dto.role.RoleResponse;
import com.khun.movievault.dto.user.UserProfileResponse;
import com.khun.movievault.dto.user.UserRequest;
import com.khun.movievault.exception.UserAlreadyExistException;
import com.khun.movievault.exception.NotFoundException;
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
    public User getUserByEmail(String email) throws NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(String.format("User with email %s is not found", email)));
    }

    @Override
    public UserProfileResponse saveUser(UserRequest userRequest) throws UserAlreadyExistException, NotFoundException {
        val existingUser = userRepository.findByEmail(userRequest.email());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException(String.format("User with EmailID: %s is already exist", userRequest.email()));
        }
        Role role = roleRepository.findById(userRequest.roleId()).orElseThrow(()-> new NotFoundException("Role Not found"));

        User user = getUser(userRequest, role);
        User savedUser = userRepository.save(user);
        return new UserProfileResponse(savedUser.getUserId(), savedUser.getEmail(), "************", new ProfileResponse(savedUser.getProfile().getProfileId(), savedUser.getProfile().getFullName(), savedUser.getProfile().getContactNo(), savedUser.getProfile().getImageUrl()), new RoleResponse(savedUser.getRole().getRoleId(), savedUser.getRole().getRoleName()));
    }

    private static User getUser(UserRequest userRequest, Role role) {
        User user = new User();
        user.setEmail(userRequest.email());
        user.setPassword(new BCryptPasswordEncoder().encode(userRequest.password()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        user.setRole(role);

        Profile profile = new Profile();
        profile.setFullName(userRequest.profile().fullName());
        profile.setContactNo(userRequest.profile().contactNo());
        profile.setImageUrl(userRequest.profile().imageUrl());

        user.setProfile(profile);
        return user;
    }

    @Override
    public UserProfileResponse updatePassword(Long userId, String password) {
        val userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            val u = userOptional.get();
            u.setPassword(password);
            val user = userRepository.save(u);
            return new UserProfileResponse(user.getUserId(), user.getEmail(), user.getPassword(), new ProfileResponse(user.getProfile().getProfileId(), user.getProfile().getFullName(), user.getProfile().getContactNo(), user.getProfile().getImageUrl()), new RoleResponse(user.getRole().getRoleId(), user.getRole().getRoleName()));
        } else {
            return null;
        }
    }
}
