package com.khun.movievault.service.impl;

import com.khun.movievault.dto.role.RoleRequest;
import com.khun.movievault.dto.role.RoleResponse;
import com.khun.movievault.model.Role;
import com.khun.movievault.repository.RoleRepository;
import com.khun.movievault.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleResponse saveRole(RoleRequest roleRequest) {
        Role role = new Role();
        role.setRoleName(roleRequest.roleName());

        Role addedRole = roleRepository.save(role);

        return new RoleResponse(addedRole.getRoleId(), addedRole.getRoleName());
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role -> new RoleResponse(role.getRoleId(), role.getRoleName())).toList();
    }
}
