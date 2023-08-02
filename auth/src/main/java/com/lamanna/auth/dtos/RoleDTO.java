package com.lamanna.auth.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class RoleDTO {

    private String role;
    private String id;
    private Date createdAt;
    private Date updatedAt;

}
