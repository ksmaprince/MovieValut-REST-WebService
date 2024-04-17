package com.khun.movievault.service.impl;

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
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponse> roleResponses = new ArrayList<>();
        roles.forEach(role ->
                roleResponses.add(new RoleResponse(role.getRoleId(), role.getRoleName()))
        );
        return roleResponses;
    }
}
