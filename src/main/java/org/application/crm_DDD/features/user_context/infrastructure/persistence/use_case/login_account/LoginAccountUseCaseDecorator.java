package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.login_account;

import org.application.crm_DDD.features.user_context.application.use_case.login_user.LoginAccountCommand;
import org.application.crm_DDD.features.user_context.application.use_case.login_user.LoginAccountResult;
import org.application.crm_DDD.features.user_context.application.use_case.login_user.LoginAccountUseCase;
import org.springframework.transaction.annotation.Transactional;

public class LoginAccountUseCaseDecorator implements LoginAccountUseCase {
    private final LoginAccountUseCase loginAccountUseCase;

    @Transactional
    @Override
    public LoginAccountResult execute(final LoginAccountCommand loginAccountCommand) {
        return this.loginAccountUseCase.execute(loginAccountCommand);
    }

    public LoginAccountUseCaseDecorator(LoginAccountUseCase loginAccountUseCase) {
        this.loginAccountUseCase = loginAccountUseCase;
    }
}
