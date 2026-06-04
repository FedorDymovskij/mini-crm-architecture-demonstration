package org.application.crm_DDD.features.identity_context.application.use_case.refresh_session;

import java.util.UUID;

public interface RefreshSessionUseCase {
    RefreshSessionResult execute(final UUID sessionId);
}
