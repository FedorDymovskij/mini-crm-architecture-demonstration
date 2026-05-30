package org.application.crm_DDD.core.security.config;


import org.application.crm_DDD.core.security.filter.authentication_filter.MainAuthenticationFilter;
import org.application.crm_DDD.core.security.filter.authentication_filter.MainAuthenticationFilterUtils;
import org.application.crm_DDD.core.security.util.CoreSecurityUtils;
import org.application.crm_DDD.core.security.util.JwtUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
@EnableMethodSecurity
public class SecurityConfiguration {


    @Bean
    public MainAuthenticationFilterUtils mainAuthenticationFilterJwtUtils(
            final @Value("${app.security.access-token.secret-key}") String secretKey,
            final @Value("${app.security.access-token.expiration-ms}") int expirationMs
    ) {
        JwtUtils jwtUtils = new JwtUtils(secretKey, expirationMs);
        return new MainAuthenticationFilterUtils(jwtUtils);
    }

    @Bean
    public MainAuthenticationFilter mainAuthenticationFilter(
            final MainAuthenticationFilterUtils mainAuthenticationFilterUtils,
            final @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver
    ) {
        return new MainAuthenticationFilter(mainAuthenticationFilterUtils, resolver);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            final HttpSecurity http,
            final MainAuthenticationFilter mainAuthenticationFilter
    ) throws Exception {
        http
                // Отключаем CSRF, так как у нас STATELESS API на токенах
                .csrf(csrf -> csrf.disable())

                // Настраиваем stateless-сессии (Spring не будет сохранять состояние между запросами)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Настраиваем правила доступа
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/refresh/**").permitAll()// Открытые эндпоинты
                        .anyRequest().authenticated() // Все остальные запросы ТРЕБУЮТ аутентификации
                );

        // Добавляем наш кастомный JWT фильтр перед стандартным UsernamePasswordAuthenticationFilter
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
