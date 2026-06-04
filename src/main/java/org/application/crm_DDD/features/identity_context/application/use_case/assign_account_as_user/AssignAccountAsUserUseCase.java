package org.application.crm_DDD.features.identity_context.application.use_case.assign_account_as_user;

import java.util.UUID;

public interface AssignAccountAsUserUseCase {
    void execute(
            final UUID initiatorAccountId,
            final UUID targetAccountId
    );
}
