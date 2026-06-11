package org.application.crm_DDD.module.identity_context.application.command.logout_account;

import jakarta.transaction.Transactional;
import org.application.crm_DDD.module.identity_context.domain.exception.IdentityContextDomainException;
import org.application.crm_DDD.module.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;
import org.application.crm_DDD.module.identity_context.domain.model.session.Session;
import org.application.crm_DDD.module.identity_context.domain.model.session.SessionId;
import org.application.crm_DDD.module.identity_context.domain.repository.SessionRepository;

import java.util.UUID;

public class LogoutAccountService implements LogoutAccountUseCase {
    private final SessionRepository sessionRepository;

    @Transactional
    @Override
    public LogoutAccountResult execute(final UUID _sessionId) {
        SessionId sessionId = new SessionId(_sessionId);
        // Initiating
        Session session = this.sessionRepository
                .findById(sessionId)
                .orElseThrow(() -> new IdentityContextDomainException("Session not found, can't logout", IdentityContextExceptionReasonPhrase.USER_SESSION_NOT_FOUND));

        // Revoking
        session.revoke();

        // Saving
        this.sessionRepository.save(session);

        // Returning result
        return new LogoutAccountResult(_sessionId);
    }

    public LogoutAccountService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
}
