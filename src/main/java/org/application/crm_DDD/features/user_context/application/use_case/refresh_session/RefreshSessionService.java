package org.application.crm_DDD.features.user_context.application.use_case.refresh_session;

import org.application.crm_DDD.features.user_context.domain.exception.UserContextDomainException;
import org.application.crm_DDD.features.user_context.domain.exception.UserContextExceptionReasonPhrase;
import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.domain.model.session.Session;
import org.application.crm_DDD.features.user_context.domain.model.session.SessionId;

public class RefreshSessionService implements RefreshSessionUseCase {
    private final RefreshSessionRepositoryPort refreshSessionRepository;
    private final RefreshSessionAccountRepositoryPort accountRepository;
    private final RefreshSessionTokenGeneratorPort tokenGenerator;

    @Override
    public RefreshSessionResult execute(final RefreshSessionCommand refreshSessionCommand) {
        SessionId oldSessionId = new SessionId(refreshSessionCommand.sessionId());

        Session oldSession = this.refreshSessionRepository
                .findById(oldSessionId)
                .orElseThrow(() -> new UserContextDomainException("Session not found, can't refresh", UserContextExceptionReasonPhrase.USER_SESSION_NOT_FOUND));

        Account account = this.accountRepository
                .findById(oldSession.getAccountId())
                .orElseThrow(() -> new UserContextDomainException("User not found, can't refresh session", UserContextExceptionReasonPhrase.USER_SESSION_NOT_FOUND));

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
