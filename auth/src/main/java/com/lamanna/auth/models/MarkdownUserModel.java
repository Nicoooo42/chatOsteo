package com.lamanna.auth.models;

import java.util.List;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "users")
public class MarkdownUserModel extends GenericModel {

    @Indexed(direction = IndexDirection.DESCENDING, unique = true)
    private String username;

    @Indexed(direction = IndexDirection.DESCENDING, unique = true)
    private String displayName;

    @Indexed(direction = IndexDirection.DESCENDING, unique = true)
    private String email;

    private List<String> roles;
    private String jwtToken;
    private String password;

    public MarkdownUserModel() {
        super();
    }
}
