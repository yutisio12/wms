package com.wms.modules.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

  private final String SECRET = "super-secret-key";

  public String generateToken(UUID userId) {

    return Jwts.builder()
        .setSubject(userId.toString())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 hari
        .signWith(SignatureAlgorithm.HS256, SECRET)
        .compact();
  }

  public String validate(String token) {
    return Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }
}