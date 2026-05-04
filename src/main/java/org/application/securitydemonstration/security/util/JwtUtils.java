package org.application.securitydemonstration.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.application.securitydemonstration.security.model.enums.AccountRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtils {

    @Value("${app.security.jwt.secret-key}")
    private String secretKey;

    @Value("${app.security.jwt.expiration-ms}")
    private int expirationMs;


    public String extractJwtTokenFromAuthorizationHeader(final HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && !header.isEmpty()) {
            String jwtToken = header.substring(7);
            if (!jwtToken.isEmpty()) {
                return jwtToken;
            }
        }
        return null;
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(this.secretKey.getBytes());
    }

    public String generateJwtToken(
            final Long userId,
            final String username,
            final AccountRole role

    ) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role.name());
        claims.put("username", username);

        return Jwts.builder()
                .subject(userId.toString())
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
        return claims.get("username", String.class);
    }

    public Date extractExpirationFromClaims(final Claims claims) throws JwtException {
        return claims.getExpiration();
    }


    public String extractRoleFromClaims(final Claims claims) throws JwtException {
        return claims.get("role", String.class);
    }


}
