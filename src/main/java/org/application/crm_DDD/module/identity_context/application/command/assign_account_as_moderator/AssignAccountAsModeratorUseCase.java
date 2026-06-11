package org.application.crm_DDD.module.identity_context.application.command.assign_account_as_moderator;

import java.util.UUID;

public interface AssignAccountAsModeratorUseCase {
    void execute(
            final UUID initiatorAccountId,
            final UUID targetAccountId
    );
}
