package com.lamanna.auth.controllers;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lamanna.auth.dtos.RoleDTO;
import com.lamanna.auth.services.RoleServiceImpl;

@RestController
@RequestMapping("/role")
@PreAuthorize("hasAnyRole('ADMIN')")
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    //- create role
    @PostMapping("/create")
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {

        checkNotNull(roleDTO);
        roleService.createRole(roleDTO);

        return roleDTO;
    }

    //- get info about a specific role
    @GetMapping("/info/{roleId}")
    public RoleDTO getRoleInfo(@PathVariable String roleId) {

        return roleService.roleInfo(roleId);
    }

//- delete a role
    //TODO: homework
//- modify a role
    //TODO: homework
}
