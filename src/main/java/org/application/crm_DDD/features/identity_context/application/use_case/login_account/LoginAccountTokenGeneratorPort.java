package org.application.crm_DDD.features.identity_context.application.use_case.login_account;


import org.application.crm_DDD.features.identity_context.domain.model.account.Account;

public interface LoginAccountTokenGeneratorPort {
    String generateAccessToken(final Account.Snapshot snapshot);
}
