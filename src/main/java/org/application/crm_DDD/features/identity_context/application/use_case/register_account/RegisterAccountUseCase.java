package org.application.crm_DDD.features.identity_context.application.use_case.register_account;

import org.application.crm_DDD.features.identity_context.domain.model.account.Account;

public interface RegisterAccountUseCase {
    Account execute(
            final String username,
            final String password
    );
}
