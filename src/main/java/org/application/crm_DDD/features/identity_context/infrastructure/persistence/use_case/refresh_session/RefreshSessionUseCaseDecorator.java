package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.refresh_session;

import org.application.crm_DDD.features.identity_context.application.use_case.refresh_session.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class RefreshSessionUseCaseDecorator implements RefreshSessionUseCase {
    private final RefreshSessionService refreshSessionService;

    @Override
    @Transactional
    public RefreshSessionResult execute(final UUID sessionId) {
        return this.refreshSessionService.execute(sessionId);
    }

    public RefreshSessionUseCaseDecorator(
            final RefreshSessionRepositoryPort refreshSessionRepositoryPort,
            final RefreshSessionAccountRepositoryPort refreshSessionAccountRepositoryPort,
            final RefreshSessionTokenGeneratorPort refreshSessionTokenGeneratorPort
    ) {
        this.refreshSessionService = new RefreshSessionService(
                refreshSessionRepositoryPort,
                refreshSessionAccountRepositoryPort,
                refreshSessionTokenGeneratorPort
        );
    }

}
