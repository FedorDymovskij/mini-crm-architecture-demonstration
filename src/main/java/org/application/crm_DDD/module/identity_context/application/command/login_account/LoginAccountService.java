package org.application.crm_DDD.module.identity_context.application.command.login_account;

import jakarta.transaction.Transactional;
import org.application.crm_DDD.module.identity_context.application.port.PasswordEncoderPort;
import org.application.crm_DDD.module.identity_context.application.port.TokenGeneratorPort;
import org.application.crm_DDD.module.identity_context.domain.exception.IdentityContextDomainException;
import org.application.crm_DDD.module.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;
import org.application.crm_DDD.module.identity_context.domain.model.account.Account;
import org.application.crm_DDD.module.identity_context.domain.model.account.AccountId;
import org.application.crm_DDD.module.identity_context.domain.model.account.Password;
import org.application.crm_DDD.module.identity_context.domain.model.account.Username;
import org.application.crm_DDD.module.identity_context.domain.model.session.Session;
import org.application.crm_DDD.module.identity_context.domain.model.session.SessionId;
import org.application.crm_DDD.module.identity_context.domain.repository.AccountRepository;
import org.application.crm_DDD.module.identity_context.domain.repository.SessionRepository;

public class LoginAccountService implements LoginAccountUseCase {
    private final AccountRepository accountRepository;
    private final SessionRepository sessionRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final TokenGeneratorPort tokenGenerator;


    @Transactional
    @Override
    public LoginAccountResult execute(
            String _username,
            String _rawPassword
    ) {
        Username username = new Username(_username);
        Password rawPassword = new Password(_rawPassword);

        // Initiating
        Account account = this.accountRepository
                .findByUsername(username)
                .orElseThrow(() -> new IdentityContextDomainException("Account not found", IdentityContextExceptionReasonPhrase.USER_UNAUTHORIZED));

        account.ensureCanLogin();

        // Checking password matching
        if (!this.passwordEncoder.matches(rawPassword, account.getPasswordHash())) {
            throw new IdentityContextDomainException(null, IdentityContextExceptionReasonPhrase.USER_UNAUTHORIZED);
        }

        // Creating new session
        SessionId sessionId = SessionId.generate();
        AccountId accountId = account.getId();
        Session session = Session.createNew(sessionId, accountId);

        // Saving session
        this.sessionRepository.save(session);

        // Creating access token
        String accessToken = this.tokenGenerator.generateAccessToken(account);

        // Returning result
        return new LoginAccountResult(session.getId().value(), accessToken);
    }

    public LoginAccountService(AccountRepository accountRepository, SessionRepository sessionRepository, PasswordEncoderPort passwordEncoder, TokenGeneratorPort tokenGenerator) {
        this.accountRepository = accountRepository;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }
}
