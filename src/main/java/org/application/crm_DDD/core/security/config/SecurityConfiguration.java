package org.application.crm_DDD.core.security.config;


import org.application.crm_DDD.core.security.filter.authentication_filter.MainAuthenticationFilter;
import org.application.crm_DDD.core.security.filter.authentication_filter.MainJwtAccessTokenUtils;
import org.application.crm_DDD.core.security.util.CoreSecurityUtils;
import org.application.crm_DDD.core.security.util.JwtUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public MainJwtAccessTokenUtils mainAuthenticationFilterJwtUtils(
            final @Value("${app.security.access-token.secret-key}") String secretKey,
            final @Value("${app.security.access-token.expiration-ms}") int expirationMs
    ) {
        JwtUtils jwtUtils = new JwtUtils(secretKey, expirationMs);
        return new MainJwtAccessTokenUtils(jwtUtils);
    }

    @Bean
    public MainAuthenticationFilter mainAuthenticationFilter(
            final MainJwtAccessTokenUtils mainJwtAccessTokenUtils,
            final @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver
    ) {
        return new MainAuthenticationFilter(mainJwtAccessTokenUtils, resolver);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            final HttpSecurity http,
            final MainAuthenticationFilter mainAuthenticationFilter
    ) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.formLogin(formLogin -> formLogin.disable());
        http.logout(logout -> logout.disable());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/register/**").permitAll()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/refresh/**").permitAll()
                .anyRequest().authenticated()
        );

        http.addFilterBefore(mainAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CoreSecurityUtils coreSecurityUtils() {
        return new CoreSecurityUtils();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
