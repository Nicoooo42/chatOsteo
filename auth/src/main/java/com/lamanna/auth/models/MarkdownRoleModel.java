package com.lamanna.auth.models;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection = "roles")
@EqualsAndHashCode(callSuper = true)
public class MarkdownRoleModel extends GenericModel {

    private String role;

    public MarkdownRoleModel() {
        super();
    }
}
