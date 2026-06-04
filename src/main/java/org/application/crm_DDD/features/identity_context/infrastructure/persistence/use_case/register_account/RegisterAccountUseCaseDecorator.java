package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.register_account;

import org.application.crm_DDD.features.identity_context.application.use_case.register_account.RegisterAccountPasswordEncoderPort;
import org.application.crm_DDD.features.identity_context.application.use_case.register_account.RegisterAccountRepositoryPort;
import org.application.crm_DDD.features.identity_context.application.use_case.register_account.RegisterAccountService;
import org.application.crm_DDD.features.identity_context.application.use_case.register_account.RegisterAccountUseCase;
import org.application.crm_DDD.features.identity_context.domain.model.account.Account;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RegisterAccountUseCaseDecorator implements RegisterAccountUseCase {
    private final RegisterAccountService registerAccountService;

    @Override
    @Transactional
    public Account execute(
            final String username,
            final String password
    ) {
        return this.registerAccountService.execute(username, password);
    }

    public RegisterAccountUseCaseDecorator(
            final RegisterAccountRepositoryPort registerUserRepositoryPort,
            final RegisterAccountPasswordEncoderPort registerUserPasswordEncoderPort
    ) {
        this.registerAccountService = new RegisterAccountService(
                registerUserRepositoryPort,
                registerUserPasswordEncoderPort
        );
    }


}
