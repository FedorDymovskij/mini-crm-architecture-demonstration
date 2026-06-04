package org.application.crm_DDD.features.identity_context.domain.model.account;

import org.application.crm_DDD.features.identity_context.application.use_case.login_account.LoginAccountPasswordEncoderPort;
import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextDomainException;
import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;

import java.util.Objects;
import java.util.UUID;

public class Account {
    private final AccountId id;
    private final Username username;
    private final Password password;
    private Role role;
    private Status status;

    public static Account register(
            final AccountId id,
            final Username username,
            final Password password
    ) {
        return new Account(
                id,
                username,
                password,
                Role.USER,
                Status.ACTIVE
        );
    }

    public void ensureActive() {
        if (this.isBlocked()) {
            throw new IdentityContextDomainException("User with username: " + this.username.value() + " is blocked", IdentityContextExceptionReasonPhrase.USER_FORBIDDEN);
        }
        if (this.isDeleted()) {
            throw new IdentityContextDomainException("User with username: " + this.username.value() + " is deleted", IdentityContextExceptionReasonPhrase.USER_FORBIDDEN);
        }
    }

    public void ensureCanBeManagedBy(final Account initiator) {
        if (this.isAdmin()) {
            throw new IdentityContextDomainException("Administrators can't be managed or modified by anyone", IdentityContextExceptionReasonPhrase.USER_FORBIDDEN);
        }
        if (initiator.isUser()) {
            throw new IdentityContextDomainException("Users don't have management privileges", IdentityContextExceptionReasonPhrase.USER_FORBIDDEN);
        }
        if (initiator.isModerator() && this.isModerator()) {
            throw new IdentityContextDomainException("Moderators can't manage other moderators", IdentityContextExceptionReasonPhrase.USER_FORBIDDEN);
        }
    }

    public void authenticate(
            final Password rawPassword,
            final LoginAccountPasswordEncoderPort encoder
    ) {
        this.ensureActive();
        if (!encoder.matches(rawPassword, this.password)) {
            throw new IdentityContextDomainException(null, IdentityContextExceptionReasonPhrase.USER_UNAUTHORIZED);
        }
    }

    public void blockBy(
            final Account initiator
    ) {
        this.ensureActive();
        initiator.ensureActive();

        if (this.id.equals(initiator.id)) {
            throw new IdentityContextDomainException("User can't block himself", IdentityContextExceptionReasonPhrase.ACCOUNT_BAD_REQUEST);
        }

        this.ensureCanBeManagedBy(initiator);

        if (this.isBlocked()) {
            throw new IdentityContextDomainException("User with username: " + this.username.value() + " is already blocked", IdentityContextExceptionReasonPhrase.ACCOUNT_BAD_REQUEST);
        }

        this.status = Status.BLOCKED;
    }


    public void unblockBy(final Account initiator) {
        initiator.ensureActive();

        if (this.isDeleted()) {
            throw new IdentityContextDomainException("Can't unblock a deleted user", IdentityContextExceptionReasonPhrase.ACCOUNT_BAD_REQUEST);
        }

        if (this.id.equals(initiator.id)) {
            throw new IdentityContextDomainException("User can't unblock himself", IdentityContextExceptionReasonPhrase.ACCOUNT_BAD_REQUEST);
        }

        this.ensureCanBeManagedBy(initiator);

        if (this.isActive()) {
            throw new IdentityContextDomainException("User is already active", IdentityContextExceptionReasonPhrase.ACCOUNT_BAD_REQUEST);
        }

        this.status = Status.ACTIVE;
    }

    public void assignAsModeratorBy(final Account initiator) {
        this.ensureActive();
        initiator.ensureActive();

        if (this.id.equals(initiator.id)) {
            throw new IdentityContextDomainException("User can't assign himself", IdentityContextExceptionReasonPhrase.USER_FORBIDDEN);
        }
        if (!initiator.isAdmin()) {
            throw new IdentityContextDomainException("Only admins can assign people", IdentityContextExceptionReasonPhrase.USER_FORBIDDEN);
        }
        if (initiator.isAdmin() && this.isAdmin()) {
            throw new IdentityContextDomainException("Admins can't demote other admins", IdentityContextExceptionReasonPhrase.USER_FORBIDDEN);
        }

        this.role = Role.MODERATOR;
    }

    public void assignAsUserBy(
            final Account initiator
    ) {
        this.ensureActive();
        initiator.ensureActive();

        if (this.id.equals(initiator.id)) {
            throw new IdentityContextDomainException("User can't assign himself", IdentityContextExceptionReasonPhrase.USER_FORBIDDEN);
        }
        if (!initiator.isAdmin()) {
            throw new IdentityContextDomainException("Only admins can assign people", IdentityContextExceptionReasonPhrase.USER_FORBIDDEN);
        }
        if (initiator.isAdmin() && this.isAdmin()) {
            throw new IdentityContextDomainException("Admins can't demote other admins", IdentityContextExceptionReasonPhrase.USER_FORBIDDEN);
        }

        this.role = Role.USER;
    }


    public boolean isBlocked() {
        return this.status.equals(Status.BLOCKED);
    }

    public boolean isDeleted() {
        return this.status.equals(Status.DELETED);
    }

    public boolean isActive() {
        return this.status.equals(Status.ACTIVE);
    }

    public boolean isUser() {
        return this.role.equals(Role.USER);
    }

    public boolean isAdmin() {
        return this.role.equals(Role.ADMIN);
    }

    public boolean isModerator() {
        return this.role.equals(Role.MODERATOR);
    }

    public record Snapshot(
            UUID id,
            String username,
            String password,
            String role,
            String status
    ) {
    }

    public Snapshot toSnapshot() {
        return new Snapshot(
                this.id.value(),
                this.username.value(),
                this.password.value(),
                this.role.name(),
                this.status.name()
        );
    }

    public static Account fromSnapshot(final Snapshot snapshot) {
        return new Account(
                new AccountId(snapshot.id()),
                new Username(snapshot.username()),
                new Password(snapshot.password()),
                Role.valueOf(snapshot.role()),
                Status.valueOf(snapshot.status())
        );
    }


    private Account(
            final AccountId id,
            final Username username,
            final Password password,
            final Role role,
            final Status status
    ) {
        this.id = Objects.requireNonNull(id);
        this.username = Objects.requireNonNull(username);
        this.password = Objects.requireNonNull(password);
        this.status = Objects.requireNonNull(status);
        this.role = Objects.requireNonNull(role);
    }

    public AccountId getId() {
        return this.id;
    }
}
