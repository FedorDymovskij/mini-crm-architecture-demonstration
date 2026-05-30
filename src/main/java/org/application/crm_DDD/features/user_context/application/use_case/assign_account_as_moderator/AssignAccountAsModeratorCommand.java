package org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_moderator;

import java.util.UUID;

public record AssignAccountAsModeratorCommand(
        UUID initiatorAccountId,
        UUID targetAccountId
) {
}
