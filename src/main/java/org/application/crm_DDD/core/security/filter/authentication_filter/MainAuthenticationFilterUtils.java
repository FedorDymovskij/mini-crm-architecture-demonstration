package org.application.crm_DDD.core.security.filter.authentication_filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.application.crm_DDD.core.security.util.JwtUtils;

import java.util.UUID;

public class MainAuthenticationFilterUtils {
    private final JwtUtils jwtUtils;


    public String extractAccessTokenFromAuthorizationHeader(final HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && !header.isEmpty()) {
            String jwtToken = header.substring(7);
            if (!jwtToken.isEmpty()) {
                return jwtToken;
            }
        }
        return null;
    }

    public Claims validateAccessTokenAndExtractAllClaims(final String jwtToken) {
        return this.jwtUtils.validateJwtTokenAndExtractAllClaims(jwtToken);
    }

    public String extractUsername(final Claims claims) {
        return claims.get("username").toString();
    }

    public String extractRole(final Claims claims) {
        return claims.get("role", String.class);
    }


    public UUID extractAccountId(final Claims claims) {
        return UUID.fromString(claims.getSubject());
    }

    public MainAuthenticationFilterUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
}
