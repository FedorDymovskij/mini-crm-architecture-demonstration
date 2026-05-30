package org.application.crm_DDD.features.user_context.infrastructure.config.use_case;

import org.application.crm_DDD.features.user_context.application.use_case.login_user.*;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.login_account.*;
import org.application.crm_DDD.features.user_context.infrastructure.security.provider.AccessTokenGeneratorProvider;
import org.application.crm_DDD.features.user_context.infrastructure.security.provider.PasswordEncoderProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginAccountConfiguration {


    @Bean
    public LoginAccountRepositoryPort loginAccountRepositoryPort(final LoginAccountRepository loginAccountRepository) {
        return new LoginAccountRepositoryAdapter(loginAccountRepository);
    }

    @Bean
    public LoginAccountPasswordEncoderPort loginAccountPasswordEncoderPort(
            final PasswordEncoderProvider passwordEncoderProvider
    ) {
        return new LoginAccountPasswordEncoderAdapter(passwordEncoderProvider);
    }

    @Bean
    public LoginAccountTokenGeneratorAdapter loginAccountTokenGeneratorAdapter(
            final AccessTokenGeneratorProvider accessTokenGeneratorProvider
    ) {
        return new LoginAccountTokenGeneratorAdapter(accessTokenGeneratorProvider);
    }

    @Bean
    public LoginAccountRefreshTokenRepositoryPort loginAccountRefreshTokenRepositoryPort(
            final LoginAccountRefreshTokenRepository loginAccountRefreshTokenRepository
    ) {
        return new LoginAccountRefreshTokenRepositoryAdapter(
                loginAccountRefreshTokenRepository
        );
    }


    @Bean
    public LoginAccountUseCase loginAccountUseCase(
            final LoginAccountRepositoryPort loginAccountRepositoryPort,
            final LoginAccountPasswordEncoderPort loginAccountPasswordEncoderPort,
            final LoginAccountRefreshTokenRepositoryPort loginAccountRefreshTokenRepositoryPort,
            final LoginAccountTokenGeneratorPort loginAccountTokenGeneratorPort
    ) {
        var loginAccountService = new LoginAccountService(
                loginAccountRepositoryPort,
                loginAccountPasswordEncoderPort,
                loginAccountRefreshTokenRepositoryPort,
                loginAccountTokenGeneratorPort
        );

        return new LoginAccountUseCaseDecorator(loginAccountService);
    }

}
