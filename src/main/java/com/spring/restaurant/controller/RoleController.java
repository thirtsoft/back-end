package com.spring.restaurant.controller;

import com.spring.restaurant.controller.api.RoleApi;
import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.model.Role;
import com.spring.restaurant.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RoleController implements RoleApi {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public ResponseEntity<List<Role>> getListRoles() {
        List<Role> roleList = roleService.findAllRoles();
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Role> getRoleById(Long idRole) {
        Role role = roleService.findRoleById(idRole)
                .orElseThrow(() -> new ResourceNotFoundException("Role Not found"));
        return ResponseEntity.ok().body(role);
    }

    @Override
    public ResponseEntity<Role> createRole(Role role) {
        Role roleResult = roleService.saveRole(role);
        return new ResponseEntity<>(roleResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Role> updateRole(Long idRole, Role role) {
        role.setId(idRole);
        Role roleResult = roleService.saveRole(role);

        return new ResponseEntity<>(roleResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteRole(Long idRol) {
        roleService.deleteRole(idRol);
        return ResponseEntity.ok().build();
    }
}
