package org.application.crm_DDD.features.user_context.application.use_case.login_user;


import org.application.crm_DDD.features.user_context.domain.model.account.Account;

public interface LoginAccountTokenGeneratorPort {
    String generateAccessToken(final Account.Snapshot snapshot);
}
