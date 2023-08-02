package com.lamanna.auth.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamanna.auth.daos.RoleDAO;
import com.lamanna.auth.dtos.RoleDTO;
import com.lamanna.auth.models.MarkdownRoleModel;

@Service
public class RoleServiceImpl {

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    ModelMapper modelMapper;


    public void createRole(RoleDTO roleDTO) {

        MarkdownRoleModel markdownRoleModel = modelMapper.map(roleDTO, MarkdownRoleModel.class);

        roleDAO.save(markdownRoleModel);

        modelMapper.map(markdownRoleModel, roleDTO);
    }

    public RoleDTO roleInfo(String roleId) {

        Optional<MarkdownRoleModel> markdownRoleModelOptional = roleDAO.findById(roleId);

        if (markdownRoleModelOptional.isPresent()) {
            final MarkdownRoleModel markdownRoleModel = markdownRoleModelOptional.get();

            return modelMapper.map(markdownRoleModel, RoleDTO.class);
        }

        return null;
    }
}
