package org.application.securitydemonstration.security.filter;

import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.application.securitydemonstration.security.model.enums.AccountRole;
import org.application.securitydemonstration.security.util.JwtUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final HandlerExceptionResolver resolver;


    public JwtFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver, JwtUtils jwtUtils) {
        this.resolver = resolver;
        this.jwtUtils = jwtUtils;
    }


    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String accessToken = this.jwtUtils.extractJwtTokenFromAuthorizationHeader(request);
            if (accessToken != null) {
                Claims claims = this.jwtUtils.validateJwtTokenAndExtractAllClaims(accessToken);
                if (claims != null) {
                    String username = jwtUtils.extractUsernameFromClaims(claims);
                    AccountRole accountRole = AccountRole.valueOf(this.jwtUtils.extractRoleFromClaims(claims));
                    if (username != null && accountRole != null) {
                        var auth = new UsernamePasswordAuthenticationToken(username, null, List.of(accountRole));
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
            }
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            this.resolver.resolveException(request, response, null, e);
        }

    }


}
