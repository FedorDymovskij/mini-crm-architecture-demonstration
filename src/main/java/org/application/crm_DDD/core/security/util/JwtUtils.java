package org.application.crm_DDD.core.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private final String secretKey;

    private final int expirationMs;


    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(this.secretKey.getBytes());
    }

    public String generateJwtToken(
            final String userId,
            final String username,
            final String role
    ) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("value", username);

        return Jwts.builder()
                .subject(userId)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + this.expirationMs))
                .claims(claims)
                .signWith(this.getSecretKey())
                .compact();
    }

    public Claims validateJwtTokenAndExtractAllClaims(final String jwtToken) throws ExpiredJwtException, SignatureException {
        return Jwts.parser()
                .verifyWith(this.getSecretKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    public String extractSubjectFromClaims(final Claims claims) throws JwtException {
        return claims.getSubject();
    }

    public String extractUsernameFromClaims(final Claims claims) throws JwtException {
        return claims.get("value", String.class);
    }

    public String extractRoleFromClaims(final Claims claims) throws JwtException {
        return claims.get("role", String.class);
    }

    public JwtUtils(String secretKey, int expirationMs) {
        this.secretKey = secretKey;
        this.expirationMs = expirationMs;
    }
}
