package org.application.crm_DDD.features.user_context.domain.model.account;

import org.application.crm_DDD.features.user_context.application.use_case.login_user.LoginAccountPasswordEncoderPort;
import org.application.crm_DDD.features.user_context.domain.exception.UserContextDomainException;
import org.application.crm_DDD.features.user_context.domain.exception.UserContextExceptionReasonPhrase;

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
            throw new UserContextDomainException("User with username: " + this.username.value() + " is blocked", UserContextExceptionReasonPhrase.USER_FORBIDDEN);
        }
        if (this.isDeleted()) {
            throw new UserContextDomainException("User with username: " + this.username.value() + " is deleted", UserContextExceptionReasonPhrase.USER_FORBIDDEN);
        }
    }

    public void ensureCanBeManagedBy(final Account initiator) {
        if (this.isAdmin()) {
            throw new UserContextDomainException("Administrators can't be managed or modified by anyone", UserContextExceptionReasonPhrase.USER_FORBIDDEN);
        }
        if (initiator.isUser()) {
            throw new UserContextDomainException("Users don't have management privileges", UserContextExceptionReasonPhrase.USER_FORBIDDEN);
        }
        if (initiator.isModerator() && this.isModerator()) {
            throw new UserContextDomainException("Moderators can't manage other moderators", UserContextExceptionReasonPhrase.USER_FORBIDDEN);
        }
    }

    public void authenticate(
            final Password rawPassword,
            final LoginAccountPasswordEncoderPort encoder
    ) {
        this.ensureActive();
        if (!encoder.matches(rawPassword, this.password)) {
            throw new UserContextDomainException(null, UserContextExceptionReasonPhrase.USER_UNAUTHORIZED);
        }
    }

    public void blockBy(
            final Account initiator
    ) {
        this.ensureActive();
        initiator.ensureActive();

        if (this.id.equals(initiator.id)) {
            throw new UserContextDomainException("User can't block himself", UserContextExceptionReasonPhrase.USER_BAD_REQUEST);
        }

        this.ensureCanBeManagedBy(initiator);

        if (this.isBlocked()) {
            throw new UserContextDomainException("User with username: " + this.username.value() + " is already blocked", UserContextExceptionReasonPhrase.USER_BAD_REQUEST);
        }

        this.status = Status.BLOCKED;
    }


    public void unblockBy(final Account initiator) {
        initiator.ensureActive();

        if (this.isDeleted()) {
            throw new UserContextDomainException("Cannot unblock a deleted user", UserContextExceptionReasonPhrase.USER_BAD_REQUEST);
        }

        if (this.id.equals(initiator.id)) {
            throw new UserContextDomainException("User can't unblock himself", UserContextExceptionReasonPhrase.USER_BAD_REQUEST);
        }

        this.ensureCanBeManagedBy(initiator);

        if (this.isActive()) {
            throw new UserContextDomainException("User is already active", UserContextExceptionReasonPhrase.USER_BAD_REQUEST);
        }

        this.status = Status.ACTIVE;
    }

    public void assignAsModeratorBy(final Account initiator) {
        this.ensureActive();
        initiator.ensureActive();

        if (!initiator.isAdmin()) {
            throw new UserContextDomainException("Only administrators can assign roles", UserContextExceptionReasonPhrase.USER_FORBIDDEN);
        }

        if (this.isAdmin()) {
            throw new UserContextDomainException("Administrators cannot be demoted or changed to moderators", UserContextExceptionReasonPhrase.USER_BAD_REQUEST);
        }

        if (this.id.equals(initiator.id)) {
            throw new UserContextDomainException("User can't change his own role", UserContextExceptionReasonPhrase.USER_BAD_REQUEST);
        }

        this.role = Role.MODERATOR;
    }

    public void assignAsUserBy(
            final Account initiator
    ) {
        this.ensureActive();
        initiator.ensureActive();

        if (this.id.equals(initiator.id)) {
            throw new UserContextDomainException("User can't assign himself", UserContextExceptionReasonPhrase.USER_BAD_REQUEST);
        }

        if (initiator.isAdmin() && !this.isAdmin()) {
            this.role = Role.USER;
        } else {
            throw new UserContextDomainException("Only admins can demote people to users, but not admins", UserContextExceptionReasonPhrase.USER_FORBIDDEN);
        }

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
                this.id.id(),
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
