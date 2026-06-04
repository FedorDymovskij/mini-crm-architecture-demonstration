package org.application.crm_DDD.features.identity_context.application.use_case.assign_account_as_moderator;

import java.util.UUID;

public interface AssignAccountAsModeratorUseCase {
    void execute(
            final UUID initiatorAccountId,
            final UUID targetAccountId
    );
}
