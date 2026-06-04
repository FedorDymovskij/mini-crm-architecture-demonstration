package org.application.crm_DDD.core.security.filter.authentication_filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.application.crm_DDD.core.security.CorePrincipal;
import org.application.crm_DDD.core.security.exception.SecurityException;
import org.application.crm_DDD.core.security.exception.SecurityExceptionReasonPhrase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


public class MainAuthenticationFilter extends OncePerRequestFilter {
    private final MainJwtAccessTokenUtils mainJwtAccessTokenUtils;
    private final HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String jwtToken = this.mainJwtAccessTokenUtils.extractAccessTokenFromAuthorizationHeader(request);
            if (jwtToken != null) {
                Claims claims = this.mainJwtAccessTokenUtils.validateAccessTokenAndExtractAllClaims(jwtToken);
                if (claims != null) {
                    UUID accountId = this.mainJwtAccessTokenUtils.extractAccountId(claims);
                    String username = this.mainJwtAccessTokenUtils.extractUsername(claims);
                    String role = this.mainJwtAccessTokenUtils.extractRole(claims);
                    if (!accountId.toString().isEmpty() && !username.isEmpty() && !role.isEmpty()) {
                        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

                        CorePrincipal principal = new CorePrincipal(accountId, username, authorities);

                        var authentication = new UsernamePasswordAuthenticationToken(principal, null, authorities);

                        authentication.setDetails(principal);

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
            filterChain.doFilter(request, response);
        } catch (final JwtException e) {
            SecurityException securityException = new SecurityException(null, SecurityExceptionReasonPhrase.SECURITY_INVALID_ACCESS_TOKEN);

            this.resolver.resolveException(request, response, null, securityException);
        }
    }

    public MainAuthenticationFilter(MainJwtAccessTokenUtils mainJwtAccessTokenUtils, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.mainJwtAccessTokenUtils = mainJwtAccessTokenUtils;
        this.resolver = resolver;
    }
}
