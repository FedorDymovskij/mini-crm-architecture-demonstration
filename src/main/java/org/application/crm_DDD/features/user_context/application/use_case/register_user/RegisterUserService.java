package org.application.crm_DDD.features.user_context.application.use_case.register_user;

import org.application.crm_DDD.features.user_context.domain.exception.UserContextDomainException;
import org.application.crm_DDD.features.user_context.domain.exception.UserContextExceptionReasonPhrase;
import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.domain.model.account.AccountId;
import org.application.crm_DDD.features.user_context.domain.model.account.Password;
import org.application.crm_DDD.features.user_context.domain.model.account.Username;

public class RegisterUserService implements RegisterUserUseCase {
    private final RegisterUserRepositoryPort registerUserRepository;
    private final RegisterUserPasswordEncoderPort registerUserPasswordEncoderPort;

    @Override
    public void execute(final RegisterUserCommand registerUserCommand) {
        Username username = new Username(registerUserCommand.username());
        Password rawPassword = new Password(registerUserCommand.password());

        if (registerUserRepository.existsByUsername(username)) {
            throw new UserContextDomainException("User with username: " + username.value() + " already exists", UserContextExceptionReasonPhrase.USER_ALREADY_EXISTS);
        }

        String passwordHash = this.registerUserPasswordEncoderPort.encode(rawPassword.value());
        Password hashedPassword = new Password(passwordHash);

        AccountId accountId = AccountId.generate();

        Account account = Account.register(
                accountId,
                username,
                hashedPassword
        );

        this.registerUserRepository.save(account);
    }

    public RegisterUserService(RegisterUserRepositoryPort registerUserRepository, RegisterUserPasswordEncoderPort registerUserPasswordEncoderPort) {
        this.registerUserRepository = registerUserRepository;
        this.registerUserPasswordEncoderPort = registerUserPasswordEncoderPort;
    }
}
