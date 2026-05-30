package org.application.crm_DDD.features.user_context.application.use_case.unblock_account;

import java.util.UUID;

public record UnblockAccountCommand(
        UUID initiatorAccountId,
        UUID targetAccountId
) {

}
