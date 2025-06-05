package com.example.ray_little.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
  @Value("${jwt.expiration}")
  private int expiration;

  @Value("${jwt.secretKey}")
  private String secretKey;

  public String generateToken(com.example.ray_little.model.User user){
    // properties => claims
    Map<String, Object> claims = new HashMap<>();
    claims.put("user_name", user.getUsername());
    try {
      return  Jwts.builder()
                       .setClaims(claims)
                       .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                       .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                       .compact();
    }catch(Exception e){
      System.err.println("Cannot create jwt token, error: " + e.getMessage());
      return null;
    }
  }

  private Key getSignInKey(){
    byte[] bytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(bytes);
  }

  public Claims extractAllClaims(String token){
    return Jwts.parserBuilder()
             .setSigningKey(getSignInKey())
             .build()
             .parseClaimsJwt(token)
             .getBody();
  }

  private <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
    final Claims claims = this.extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  // check expiration
  public boolean isTokenExpired(String token){
    Date expirationDate = this.extractClaims(token, Claims::getExpiration);
    return expirationDate.before(new Date());
  }
}
