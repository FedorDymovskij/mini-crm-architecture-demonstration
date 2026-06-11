package org.application.crm_DDD.module.identity_context.application.port;

import org.application.crm_DDD.module.identity_context.domain.model.account.Account;

public interface TokenGeneratorPort {
    String generateAccessToken(final Account account);
}
