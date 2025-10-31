package com.mojistep.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
  @Value("${app.jwt.secret}") private String secret;
  @Value("${app.jwt.issuer}") private String issuer;
  @Value("${app.jwt.access-token-ttl}") private String accessTtl;
  @Value("${app.jwt.refresh-token-ttl}") private String refreshTtl;

  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String generateToken(String subject, Map<String, Object> claims, boolean refresh) {
    long ttlMillis = java.time.Duration.parse(refresh ? refreshTtl : accessTtl).toMillis();
    Date now = new Date();
    return Jwts.builder()
      .setIssuer(issuer)
      .setSubject(subject)
      .addClaims(claims)
      .setIssuedAt(now)
      .setExpiration(new Date(now.getTime() + ttlMillis))
      .signWith(getSigningKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    return claimsResolver.apply(claims);
  }
}
