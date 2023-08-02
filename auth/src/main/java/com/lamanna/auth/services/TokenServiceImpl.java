package com.lamanna.auth.services;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamanna.auth.exceptions.InvalidTokenException;
import com.lamanna.auth.models.MarkdownUserModel;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;



@Service
@Log4j2
public class TokenServiceImpl {

    @Autowired
    AuthSigningKeyResolverImpl authSigningKeyResolver;

    public void validateToken(String jwtToken) throws InvalidTokenException {

        checkNotNull(jwtToken);

        try {
            Jwts.parserBuilder()
                .setSigningKeyResolver(authSigningKeyResolver)
                .build()
                .parse(jwtToken);

        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            log.warn("Exception at token validation");
            throw new InvalidTokenException("Invalid token", e);
        }
    }

    public void generateToken(MarkdownUserModel markdownUserModel) {

        String jwtToken;
        jwtToken = Jwts.builder()
                    .setSubject(markdownUserModel.getUsername())
                    .setAudience(markdownUserModel.getRoles().toString())
                    .setIssuer(markdownUserModel.getId())
                    .signWith(authSigningKeyResolver.getSecretKey(), SignatureAlgorithm.HS512)
                    .compact();

        markdownUserModel.setJwtToken(jwtToken);
    }

    public List<String> getRolesFromToken(String jwtToken) {

        if (isEmpty(jwtToken)) {
            return new ArrayList<>();
        }

        String claims = new String(Base64.getUrlDecoder().decode(jwtToken.split("\\.")[1]));
        JSONObject claimsJson = new JSONObject(claims);

        // "[ADMIN, USER]"
        String audience = claimsJson.getString("aud");
        final String[] split = audience
                .replace("[", "")
                .replace("]", "")
                .split(",");

        return Stream.of(split).map(String::trim).collect(Collectors.toList());
    }


    public String getUserIdFromToken(String jwtToken) {
        if (isEmpty(jwtToken)) {
            return StringUtils.EMPTY;
        }

        // abc.123.awe
        String claims = new String(Base64.getUrlDecoder().decode(jwtToken.split("\\.")[1]));
        JSONObject claimsJson = new JSONObject(claims);

        return claimsJson.getString("iss");
    }
}
