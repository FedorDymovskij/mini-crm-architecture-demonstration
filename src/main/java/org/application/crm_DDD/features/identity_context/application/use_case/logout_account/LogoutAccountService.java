package org.application.crm_DDD.features.identity_context.application.use_case.logout_account;

import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextDomainException;
import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;
import org.application.crm_DDD.features.identity_context.domain.model.session.Session;
import org.application.crm_DDD.features.identity_context.domain.model.session.SessionId;

import java.util.UUID;

public class LogoutAccountService implements LogoutAccountUseCase {
    private final LogoutAccountSessionRepositoryPort logoutAccountSessionRepositoryPort;

    @Override
    public LogoutAccountResult execute(final UUID _sessionId) {
        SessionId sessionId = new SessionId(_sessionId);

        Session session = this.logoutAccountSessionRepositoryPort
                .findById(sessionId)
                .orElseThrow(() -> new IdentityContextDomainException("Session not found, can't logout", IdentityContextExceptionReasonPhrase.USER_SESSION_NOT_FOUND));

        session.revoke();

        this.logoutAccountSessionRepositoryPort.save(session);

        return new LogoutAccountResult(_sessionId);
    }

    public LogoutAccountService(LogoutAccountSessionRepositoryPort logoutAccountSessionRepositoryPort) {
        this.logoutAccountSessionRepositoryPort = logoutAccountSessionRepositoryPort;
    }
}
