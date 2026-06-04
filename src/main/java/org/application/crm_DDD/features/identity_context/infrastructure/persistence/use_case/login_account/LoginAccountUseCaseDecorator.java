package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.login_account;

import org.application.crm_DDD.features.identity_context.application.use_case.login_account.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LoginAccountUseCaseDecorator implements LoginAccountUseCase {
    private final LoginAccountService loginAccountService;

    @Override
    @Transactional
    public LoginAccountResult execute(
            final String username,
            final String rawPassword
    ) {
        return this.loginAccountService.execute(username, rawPassword);
    }

    public LoginAccountUseCaseDecorator(
            final LoginAccountRepositoryPort loginAccountRepositoryPort,
            final LoginAccountPasswordEncoderPort loginAccountPasswordEncoderPort,
            final LoginAccountSessionRepositoryPort loginAccountRefreshTokenRepositoryPort,
            final LoginAccountTokenGeneratorPort loginAccountTokenGeneratorPort
    ) {
        this.loginAccountService = new LoginAccountService(
                loginAccountRepositoryPort,
                loginAccountPasswordEncoderPort,
                loginAccountRefreshTokenRepositoryPort,
                loginAccountTokenGeneratorPort
        );
    }
}
