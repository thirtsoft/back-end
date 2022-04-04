package com.spring.restaurant.controller.api;

import com.spring.restaurant.model.Role;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.spring.restaurant.util.Constants.APP_ROOT;

public interface RoleApi {

    @GetMapping(value = APP_ROOT + "/roles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Role>>  getListRoles();

    @GetMapping(value = APP_ROOT + "/roles/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long idRole);

    @PostMapping(value = APP_ROOT + "/roles/create")
    ResponseEntity<Role> createRole(@RequestBody Role role);

    @PutMapping(value = APP_ROOT + "/roles/update/{idRole}")
    ResponseEntity<Role> updateRole(@PathVariable(value = "idRole") Long idRole, @RequestBody Role role);

    @DeleteMapping(value = APP_ROOT + "/roles/delete/{idRole}")
    ResponseEntity<Object> deleteRole(@PathVariable(value = "idRole") Long idRol);
}
