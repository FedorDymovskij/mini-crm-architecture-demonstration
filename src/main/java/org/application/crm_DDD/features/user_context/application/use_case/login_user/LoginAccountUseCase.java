package org.application.crm_DDD.features.user_context.application.use_case.login_user;

public interface LoginAccountUseCase {
    LoginAccountResult execute(final LoginAccountCommand loginAccountCommand);
}
