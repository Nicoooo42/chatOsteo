package com.lamanna.auth.daos;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lamanna.auth.models.MarkdownRoleModel;

@Repository
public interface RoleDAO extends MongoRepository<MarkdownRoleModel, String> {

    Optional<MarkdownRoleModel> findByRole(String role);
}
