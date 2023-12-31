package com.lamanna.auth.controllers;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lamanna.auth.dtos.UserInfoDTO;
import com.lamanna.auth.dtos.UserLoginDTO;
import com.lamanna.auth.services.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserServiceImpl userService;

    //    - create user
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ANONYMOUS', 'ADMIN')")
    public UserInfoDTO createUser(@RequestBody UserInfoDTO userInfoDTO) {

        checkNotNull(userInfoDTO);

        userService.createUser(userInfoDTO);

        return userInfoDTO;
    }

    //- get info about a specific user
    @GetMapping("/info/{userId}")
    @PreAuthorize("hasAnyRole('ANONYMOUS', 'USER', 'ADMIN')")
    public UserInfoDTO getUserInfo(@PathVariable String userId, HttpServletRequest request) {

        String tokenUnstripped = request.getHeader(AUTHORIZATION);
        String token = null;
        if(!isEmpty(tokenUnstripped)) {
           token = StringUtils.removeStart(tokenUnstripped, "Bearer").trim();
        }

        return userService.retrieveUserInfo(userId, token);
    }

    @PostMapping("/login")
    @PreAuthorize("hasAnyRole('ANONYMOUS')")
    public UserInfoDTO loginUser(@RequestBody UserLoginDTO userLoginDTO) {

        checkNotNull(userLoginDTO);

        return userService.loginUser(userLoginDTO);
    }

//- delete a user
    // TODO: homework
//- modify a user
    // TODO: homework
}
