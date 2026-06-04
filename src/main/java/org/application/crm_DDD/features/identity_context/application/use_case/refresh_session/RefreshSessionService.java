package org.application.crm_DDD.features.identity_context.application.use_case.refresh_session;

import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextDomainException;
import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;
import org.application.crm_DDD.features.identity_context.domain.model.account.Account;
import org.application.crm_DDD.features.identity_context.domain.model.session.Session;
import org.application.crm_DDD.features.identity_context.domain.model.session.SessionId;

import java.util.UUID;

public class RefreshSessionService implements RefreshSessionUseCase {
    private final RefreshSessionRepositoryPort refreshSessionRepository;
    private final RefreshSessionAccountRepositoryPort accountRepository;
    private final RefreshSessionTokenGeneratorPort tokenGenerator;

    @Override
    public RefreshSessionResult execute(final UUID sessionId) {
        SessionId oldSessionId = new SessionId(sessionId);

        Session oldSession = this.refreshSessionRepository
                .findById(oldSessionId)
                .orElseThrow(() -> new IdentityContextDomainException("Session not found, can't refresh", IdentityContextExceptionReasonPhrase.USER_SESSION_NOT_FOUND));

        Account account = this.accountRepository
                .findById(oldSession.getAccountId())
                .orElseThrow(() -> new IdentityContextDomainException("User not found, can't refresh session", IdentityContextExceptionReasonPhrase.USER_SESSION_NOT_FOUND));

        SessionId newSessionId = SessionId.generate();

        Session newSession = oldSession.refresh(newSessionId);

        this.refreshSessionRepository.save(oldSession);
        this.refreshSessionRepository.save(newSession);

        String accessToken = this.tokenGenerator.generateAccessToken(account.toSnapshot());

        return new RefreshSessionResult(newSession.getId().value(), accessToken, Session.EXPIRATION_MINUTES);
    }

    public RefreshSessionService(RefreshSessionRepositoryPort refreshSessionRepository, RefreshSessionAccountRepositoryPort accountRepository, RefreshSessionTokenGeneratorPort tokenGenerator) {
        this.refreshSessionRepository = refreshSessionRepository;
        this.accountRepository = accountRepository;
        this.tokenGenerator = tokenGenerator;
    }
}
