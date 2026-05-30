package org.application.crm_DDD.features.user_context.application.use_case.block_account;

import java.util.UUID;

public record BlockAccountCommand(
        UUID initiatorAccountId,
        UUID targetAccountId
) {

}
