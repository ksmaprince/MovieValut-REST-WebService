package com.khun.movievault.service;

import com.khun.movievault.dto.role.RoleRequest;
import com.khun.movievault.dto.role.RoleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    RoleResponse saveRole(RoleRequest roleRequest);
    List<RoleResponse> getAllRoles();
}
