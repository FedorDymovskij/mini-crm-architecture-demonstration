package org.application.crm_DDD.module.identity_context.application.command.assign_account_as_user;

import java.util.UUID;

public interface AssignAccountAsUserUseCase {
    void execute(
            final UUID initiatorAccountId,
            final UUID targetAccountId
    );
}
