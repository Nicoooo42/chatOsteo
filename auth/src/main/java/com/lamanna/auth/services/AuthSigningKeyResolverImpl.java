package com.lamanna.auth.services;

import static java.util.Objects.isNull;

import java.security.Key;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.security.Keys;

@Component
public class AuthSigningKeyResolverImpl implements SigningKeyResolver  {

   @Value("${jwt.secret.key}")
   String secretKeyString;

   private SecretKey secretKey;

   public SecretKey getSecretKey() {

       if (isNull(secretKey)) {
           this.secretKey = Keys.hmacShaKeyFor(Base64.getEncoder().encode(this.secretKeyString.getBytes()));
       }

       return this.secretKey;
   }

   public Key resolveSigningKey(JwsHeader header, Claims claims) {
       return getSecretKey();
   }

   public Key resolveSigningKey(JwsHeader header, String plaintext) {
       return getSecretKey();
   }
}
