package org.application.crm_DDD.module.identity_context.application.command.register_account;

import jakarta.transaction.Transactional;
import org.application.crm_DDD.module.identity_context.application.port.PasswordEncoderPort;
import org.application.crm_DDD.module.identity_context.domain.exception.IdentityContextDomainException;
import org.application.crm_DDD.module.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;
import org.application.crm_DDD.module.identity_context.domain.model.account.Account;
import org.application.crm_DDD.module.identity_context.domain.model.account.AccountId;
import org.application.crm_DDD.module.identity_context.domain.model.account.Password;
import org.application.crm_DDD.module.identity_context.domain.model.account.Username;
import org.application.crm_DDD.module.identity_context.domain.repository.AccountRepository;

public class RegisterAccountService implements RegisterAccountUseCase {
    private final AccountRepository accountRepository;
    private final PasswordEncoderPort passwordEncoder;

    @Transactional
    @Override
    public Account execute(
            final String _username,
            final String rawPassword
    ) {
        Username username = new Username(_username);

        // Checking uniqueness
        if (accountRepository.existsByUsername(username)) {
            throw new IdentityContextDomainException("Account with username: " + username.value() + " already exists", IdentityContextExceptionReasonPhrase.ACCOUNT_ALREADY_EXISTS);
        }

        // Generating password hash
        String passwordHash = this.passwordEncoder.encode(rawPassword);

        // Registering
        Account account = Account.register(
                AccountId.generate(),
                username,
                new Password(passwordHash)
        );

        // Saving
        this.accountRepository.save(account);

        // Returning result
        return account;
    }

    public RegisterAccountService(AccountRepository accountRepository, PasswordEncoderPort passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
