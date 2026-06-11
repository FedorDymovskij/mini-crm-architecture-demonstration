package org.application.crm_DDD.module.identity_context.application.command.register_account;

import org.application.crm_DDD.module.identity_context.domain.model.account.Account;

public interface RegisterAccountUseCase {
    Account execute(
            final String username,
            final String password
    );
}
