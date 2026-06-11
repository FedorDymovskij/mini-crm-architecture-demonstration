package org.application.crm_DDD.module.identity_context.application.command.refresh_session;

import java.util.UUID;

public interface RefreshSessionUseCase {
    RefreshSessionResult execute(final UUID sessionId);
}
