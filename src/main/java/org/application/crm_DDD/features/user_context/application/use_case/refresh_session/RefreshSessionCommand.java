package org.application.crm_DDD.features.user_context.application.use_case.refresh_session;

import java.util.UUID;

public record RefreshSessionCommand(
        UUID sessionId
) {


}
