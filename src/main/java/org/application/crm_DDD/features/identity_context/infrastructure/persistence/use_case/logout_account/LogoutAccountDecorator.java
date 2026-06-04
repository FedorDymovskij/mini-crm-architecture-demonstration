package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.logout_account;

import org.application.crm_DDD.features.identity_context.application.use_case.logout_account.LogoutAccountResult;
import org.application.crm_DDD.features.identity_context.application.use_case.logout_account.LogoutAccountService;
import org.application.crm_DDD.features.identity_context.application.use_case.logout_account.LogoutAccountSessionRepositoryPort;
import org.application.crm_DDD.features.identity_context.application.use_case.logout_account.LogoutAccountUseCase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class LogoutAccountDecorator implements LogoutAccountUseCase {
    private final LogoutAccountService logoutAccountService;

    @Override
    @Transactional
    public LogoutAccountResult execute(final UUID sessionId) {
        return logoutAccountService.execute(sessionId);
    }

    public LogoutAccountDecorator(LogoutAccountSessionRepositoryPort logoutAccountSessionRepositoryPort) {
        this.logoutAccountService = new LogoutAccountService(logoutAccountSessionRepositoryPort);
    }
}
