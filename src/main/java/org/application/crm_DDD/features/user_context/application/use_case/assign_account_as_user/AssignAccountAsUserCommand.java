package org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_user;

import java.util.UUID;

public record AssignAccountAsUserCommand(
        UUID initiatorAccountId,
        UUID targetAccountId
) {
}
