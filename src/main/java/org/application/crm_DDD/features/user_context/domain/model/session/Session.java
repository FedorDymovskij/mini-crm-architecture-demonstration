package org.application.crm_DDD.features.user_context.domain.model.session;

import org.application.crm_DDD.features.user_context.domain.exception.UserContextDomainException;
import org.application.crm_DDD.features.user_context.domain.exception.UserContextExceptionReasonPhrase;
import org.application.crm_DDD.features.user_context.domain.model.account.AccountId;

import java.time.LocalDateTime;
import java.util.UUID;

public class Session {
    private final SessionId id;
    private final AccountId accountId;
    private final Expiration expiration;
    private Status status;

    public static final long EXPIRATION_MINUTES = 7 * 24 * 60;

    public static Session createNew(
            final SessionId sessionId,
            final AccountId accountId
    ) {
        Expiration expiration = new Expiration(LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES));

        return new Session(
                sessionId,
                accountId,
                expiration,
                Status.ACTIVE
        );
    }


    public void ensureActive() {
        if (this.isRevoked()) {
            throw new UserContextDomainException("Session is revoked", UserContextExceptionReasonPhrase.USER_SESSION_REVOKED);
        }
        if (this.isExpired()) {
            throw new UserContextDomainException("Session is expired", UserContextExceptionReasonPhrase.USER_SESSION_EXPIRED);
        }
        if (this.isRefreshed()) {
            throw new UserContextDomainException("Session is refreshed", UserContextExceptionReasonPhrase.USER_SESSION_REFRESHED);
        }
    }


    public Session refresh(
            final SessionId newSessionId
    ) {
        this.ensureActive();
        this.status = Status.REFRESHED;
        return createNew(newSessionId, this.getAccountId());
    }

    public boolean isActive() {
        return this.status.equals(Status.ACTIVE);
    }

    public boolean isRevoked() {
        return this.status.equals(Status.REVOKED);
    }

    public boolean isExpired() {
        if (LocalDateTime.now().isAfter(this.expiration.value()) || this.status.equals(Status.EXPIRED)) {
            this.status = Status.EXPIRED;
            return true;
        }
        return false;
    }

    public boolean isRefreshed() {
        return this.status.equals(Status.REFRESHED);
    }

    public record Snapshot(
            UUID id,
            UUID accountId,
            LocalDateTime expiration,
            String status
    ) {
    }

    public Snapshot toSnapshot() {
        return new Snapshot(
                this.id.value(),
                this.accountId.value(),
                this.expiration.value(),
                this.status.name()
        );
    }

    public static Session fromSnapshot(final Snapshot snapshot) {
        return new Session(
                new SessionId(snapshot.id()),
                new AccountId(snapshot.accountId()),
                new Expiration(snapshot.expiration()),
                Status.valueOf(snapshot.status())
        );
    }

    private Session(
            final SessionId id,
            final AccountId accountId,
            final Expiration expiration,
            final Status status
    ) {
        this.id = id;
        this.accountId = accountId;
        this.expiration = expiration;
        this.status = status;
    }

    public AccountId getAccountId() {
        return this.accountId;
    }

    public SessionId getId() {
        return this.id;
    }
}
