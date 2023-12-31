package com.lamanna.auth.services;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lamanna.auth.daos.RoleDAO;
import com.lamanna.auth.daos.UserDAO;
import com.lamanna.auth.dtos.UserInfoDTO;
import com.lamanna.auth.dtos.UserLoginDTO;
import com.lamanna.auth.models.MarkdownRoleModel;
import com.lamanna.auth.models.MarkdownUserModel;

/**
 * This file was created by aantonica on 19/05/2020
 */
@Service
public class UserServiceImpl {

    private static final String UNKNOWN_USERNAME_OR_BAD_PASSWORD = "Unknown username or bad password";
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TokenServiceImpl tokenService;

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createUser(UserInfoDTO userInfoDTO) {

        checkNotNull(userInfoDTO.getPassword());

        // transform userInfoDTO to markdownRoleModel
        MarkdownUserModel markdownUserModel = modelMapper.map(userInfoDTO, MarkdownUserModel.class);

        // hash the password first
        markdownUserModel.setPassword(bCryptPasswordEncoder.encode(userInfoDTO.getPassword()));


        // assign default role of USER
        markdownUserModel.setRoles(
            roleDAO.findAll().stream()
                .map(MarkdownRoleModel::getRole)
                .filter(role -> role.contains("USER"))
                .collect(Collectors.toList())
        );

        // generate a new token for the user
        tokenService.generateToken(markdownUserModel);

        // save markdownRoleModel
        userDAO.save(markdownUserModel);

        // update the userInfoDTO after the markdownRoleModel has been saved
        userInfoDTO.setPassword("");
        modelMapper.map(markdownUserModel, userInfoDTO);
    }

    public UserInfoDTO retrieveUserInfo(String userId, String token) {

        Optional<MarkdownUserModel> optionalMarkdownUserModel = userDAO.findById(userId);

        String userIdFromToken = this.tokenService.getUserIdFromToken(token);
        List<String> userRolesFromToken = this.tokenService.getRolesFromToken(token);

        if (optionalMarkdownUserModel.isPresent()) {

            final MarkdownUserModel markdownUserModel = optionalMarkdownUserModel.get();

            if (userIdFromToken.equals(userId) || userRolesFromToken.contains("ADMIN")) {
                return modelMapper.map(markdownUserModel, UserInfoDTO.class);
            }

            UserInfoDTO userInfoDTO = new UserInfoDTO();
            userInfoDTO.setDisplayName(markdownUserModel.getDisplayName());
            return userInfoDTO;
        }

        return null;
    }


    public UserInfoDTO loginUser(UserLoginDTO userLoginDTO) {

        Optional<MarkdownUserModel> optionalMarkdownUserModel = userDAO.findByUsername(userLoginDTO.getUsername());

        if (optionalMarkdownUserModel.isPresent()) {
            MarkdownUserModel markdownUserModel = optionalMarkdownUserModel.get();

             // check if passwords match
             if (bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), markdownUserModel.getPassword())) {

                 return modelMapper.map(markdownUserModel, UserInfoDTO.class);
             } else {
                 throw new BadCredentialsException(UNKNOWN_USERNAME_OR_BAD_PASSWORD);
             }
        } else {
            throw new BadCredentialsException(UNKNOWN_USERNAME_OR_BAD_PASSWORD);
        }
    }
}
