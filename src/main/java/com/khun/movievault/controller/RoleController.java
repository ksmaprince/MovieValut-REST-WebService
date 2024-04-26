package com.khun.movievault.controller;

import com.khun.movievault.dto.role.RoleRequest;
import com.khun.movievault.dto.role.RoleResponse;
import com.khun.movievault.model.Role;
import com.khun.movievault.service.RoleService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movievault/v1/api/auth")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/addRole")
    public ResponseEntity<RoleResponse> saveRoll(@RequestBody RoleRequest roleRequest){
        return ResponseEntity.ok(roleService.saveRole(roleRequest));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleResponse>> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
