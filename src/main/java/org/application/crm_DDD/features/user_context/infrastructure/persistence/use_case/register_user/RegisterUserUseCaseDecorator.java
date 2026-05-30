package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.register_user;

import org.application.crm_DDD.features.user_context.application.use_case.register_user.RegisterUserCommand;
import org.application.crm_DDD.features.user_context.application.use_case.register_user.RegisterUserUseCase;
import org.springframework.transaction.annotation.Transactional;

public class RegisterUserUseCaseDecorator implements RegisterUserUseCase {
    private final RegisterUserUseCase registerUserUseCase;

    @Transactional
    @Override
    public void execute(final RegisterUserCommand command) {
        this.registerUserUseCase.execute(command);
    }

    public RegisterUserUseCaseDecorator(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }
}
