package org.application.crm_DDD.features.identity_context.application.use_case.block_account;

import java.util.UUID;

public interface BlockAccountUseCase {
    void execute(
            final UUID initiatorAccountId,
            final UUID targetAccountId
    );
}
