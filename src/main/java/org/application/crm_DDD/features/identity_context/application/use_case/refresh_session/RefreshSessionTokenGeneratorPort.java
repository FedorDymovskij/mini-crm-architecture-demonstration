package org.application.crm_DDD.features.identity_context.application.use_case.refresh_session;

import org.application.crm_DDD.features.identity_context.domain.model.account.Account;

public interface RefreshSessionTokenGeneratorPort {
    String generateAccessToken(final Account.Snapshot snapshot);
}
