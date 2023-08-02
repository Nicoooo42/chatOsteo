package com.lamanna.auth.controllers;


import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lamanna.auth.services.TokenServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

/**
 * This file was created by aantonica on 19/05/2020
 */

 @RestController
 @RequestMapping("/token")
 @Log4j2
public class TokenController {

    @Autowired
    TokenServiceImpl tokenService;

    @GetMapping("/validate")
    @PreAuthorize("hasAnyRole('ANONYMOUS', 'USER', 'ADMIN')")
    public void validateToken(HttpServletRequest httpServletRequest) throws Exception {

        String authHeader = httpServletRequest.getHeader(AUTHORIZATION);
//        Bearer 4jkn2.24kn234kj2n
        String token = null;

        log.info("Started validating header " + authHeader);
        if (!isEmpty(authHeader)) {
            token = authHeader.split("\\s")[1];
        } else {
            log.info("Nothing to validate");
            return;
        }

        log.info("Validating");
        tokenService.validateToken(token);
    }
}
