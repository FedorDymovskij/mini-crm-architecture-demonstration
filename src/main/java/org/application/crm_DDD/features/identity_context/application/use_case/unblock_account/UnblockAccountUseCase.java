package org.application.crm_DDD.features.identity_context.application.use_case.unblock_account;

import java.util.UUID;

public interface UnblockAccountUseCase {
    void execute(
            final UUID initiatorAccountId,
            final UUID targetAccountId
    );
}
