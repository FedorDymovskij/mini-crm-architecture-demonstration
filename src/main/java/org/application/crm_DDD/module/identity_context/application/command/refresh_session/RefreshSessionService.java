package org.application.crm_DDD.module.identity_context.application.command.refresh_session;

import org.application.crm_DDD.module.identity_context.application.port.TokenGeneratorPort;
import org.application.crm_DDD.module.identity_context.domain.exception.IdentityContextDomainException;
import org.application.crm_DDD.module.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;
import org.application.crm_DDD.module.identity_context.domain.model.account.Account;
import org.application.crm_DDD.module.identity_context.domain.model.session.Session;
import org.application.crm_DDD.module.identity_context.domain.model.session.SessionId;
import org.application.crm_DDD.module.identity_context.domain.repository.AccountRepository;
import org.application.crm_DDD.module.identity_context.domain.repository.SessionRepository;

import java.util.UUID;

public class RefreshSessionService implements RefreshSessionUseCase {

    private final AccountRepository accountRepository;
    private final SessionRepository sessionRepository;
    private final TokenGeneratorPort tokenGenerator;

    @Override
    public RefreshSessionResult execute(final UUID sessionId) {
        SessionId oldSessionId = new SessionId(sessionId);

        // Initiating
        Session oldSession = this.sessionRepository
                .findById(oldSessionId)
                .orElseThrow(() -> new IdentityContextDomainException("Session not found, can't refresh", IdentityContextExceptionReasonPhrase.USER_SESSION_NOT_FOUND));

        Account account = this.accountRepository
                .findById(oldSession.getAccountId())
                .orElseThrow(() -> new IdentityContextDomainException("User not found, can't refresh session", IdentityContextExceptionReasonPhrase.USER_SESSION_NOT_FOUND));

        // Refreshing
        Session newSession = oldSession.refresh(SessionId.generate());

        // Saving old session
        this.sessionRepository.save(oldSession);

        // Saving new session
        this.sessionRepository.save(newSession);

        // Generating access token
        String accessToken = this.tokenGenerator.generateAccessToken(account);

        // Returning result
        return new RefreshSessionResult(newSession.getId().value(), accessToken);
    }

    public RefreshSessionService(AccountRepository accountRepository, SessionRepository sessionRepository, TokenGeneratorPort tokenGenerator) {
        this.accountRepository = accountRepository;
        this.sessionRepository = sessionRepository;
        this.tokenGenerator = tokenGenerator;
    }
}
