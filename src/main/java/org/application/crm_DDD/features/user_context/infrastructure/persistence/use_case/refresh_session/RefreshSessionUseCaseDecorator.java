package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.refresh_session;

import org.application.crm_DDD.features.user_context.application.use_case.refresh_session.RefreshSessionCommand;
import org.application.crm_DDD.features.user_context.application.use_case.refresh_session.RefreshSessionResult;
import org.application.crm_DDD.features.user_context.application.use_case.refresh_session.RefreshSessionService;
import org.application.crm_DDD.features.user_context.application.use_case.refresh_session.RefreshSessionUseCase;
import org.springframework.transaction.annotation.Transactional;

public class RefreshSessionUseCaseDecorator implements RefreshSessionUseCase {
    private final RefreshSessionService refreshSessionService;

    @Override
    @Transactional
    public RefreshSessionResult execute(RefreshSessionCommand refreshSessionCommand) {
        return this.refreshSessionService.execute(refreshSessionCommand);
    }

    public RefreshSessionUseCaseDecorator(RefreshSessionService refreshSessionService) {
        this.refreshSessionService = refreshSessionService;
    }
}
