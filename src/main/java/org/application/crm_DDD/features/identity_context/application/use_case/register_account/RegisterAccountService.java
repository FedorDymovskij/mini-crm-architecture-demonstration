package org.application.crm_DDD.features.identity_context.application.use_case.register_account;

import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextDomainException;
import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;
import org.application.crm_DDD.features.identity_context.domain.model.account.Account;
import org.application.crm_DDD.features.identity_context.domain.model.account.AccountId;
import org.application.crm_DDD.features.identity_context.domain.model.account.Password;
import org.application.crm_DDD.features.identity_context.domain.model.account.Username;

public class RegisterAccountService implements RegisterAccountUseCase {
    private final RegisterAccountRepositoryPort registerUserRepository;
    private final RegisterAccountPasswordEncoderPort registerUserPasswordEncoderPort;

    @Override
    public Account execute(
            final String username,
            final String rawPassword
    ) {
        Username regUsername = new Username(username);

        if (registerUserRepository.existsByUsername(regUsername)) {
            throw new IdentityContextDomainException("User with value: " + regUsername.value() + " already exists", IdentityContextExceptionReasonPhrase.ACCOUNT_ALREADY_EXISTS);
        }

        String passwordHash = this.registerUserPasswordEncoderPort.encode(rawPassword);
        Password hashedPassword = new Password(passwordHash);

        AccountId accountId = AccountId.generate();

        Account account = Account.register(
                accountId,
                regUsername,
                hashedPassword
        );

        this.registerUserRepository.save(account);

        return account;
    }

    public RegisterAccountService(RegisterAccountRepositoryPort registerUserRepository, RegisterAccountPasswordEncoderPort registerUserPasswordEncoderPort) {
        this.registerUserRepository = registerUserRepository;
        this.registerUserPasswordEncoderPort = registerUserPasswordEncoderPort;
    }
}
