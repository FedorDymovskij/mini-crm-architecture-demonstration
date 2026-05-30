package org.application.crm_DDD.features.user_context.application.use_case.refresh_session;

public interface RefreshSessionUseCase {
    RefreshSessionResult execute(final RefreshSessionCommand refreshSessionCommand);
}
