package com.spring.restaurant.service;

import com.spring.restaurant.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role saveRole(Role role);

    Role updateRole(Long idRole, Role role);

    Optional<Role> findRoleById(Long idRole);

    List<Role> findAllRoles();

    void deleteRole(Long idRole);
}
