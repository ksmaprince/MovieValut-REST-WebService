package com.khun.movievault.service;

import com.khun.movievault.dto.role.RoleResponse;
import com.khun.movievault.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    Role saveRole(Role role);
    List<RoleResponse> getAllRoles();
}
