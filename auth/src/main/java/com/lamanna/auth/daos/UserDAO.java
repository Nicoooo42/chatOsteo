package com.lamanna.auth.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lamanna.auth.models.MarkdownUserModel;

@Repository
public interface UserDAO extends MongoRepository<MarkdownUserModel, String> {

   Optional<MarkdownUserModel> findByUsername(String username);
   Optional<MarkdownUserModel> findByJwtToken(String jwtToken);
   List<MarkdownUserModel> findByDisplayNameOrUsernameOrEmail(String username);
}
