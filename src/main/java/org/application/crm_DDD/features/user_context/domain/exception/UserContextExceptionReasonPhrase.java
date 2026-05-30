package org.application.crm_DDD.features.user_context.domain.exception;

import org.application.crm_DDD.core.exception.ExceptionReasonPhraseSpecification;

public enum UserContextExceptionReasonPhrase implements ExceptionReasonPhraseSpecification {
    USER_UNAUTHORIZED("User not authorized"),
    USER_FORBIDDEN("Forbidden action for user"),
    USER_BAD_REQUEST("Bad request"),
    USER_CONFLICT("Conflict"),
    USER_NOT_FOUND("User not found"),
    USER_ALREADY_EXISTS("User already exists"),
    USER_SESSION_NOT_FOUND("User session not found"),
    USER_SESSION_EXPIRED("User session expired"),
    USER_SESSION_REVOKED("User session revoked"),
    USER_SESSION_REFRESHED("User session refreshed"),
    USER_DATA_VALIDATION_FAILED("Data validation failed");

    private final String message;

    UserContextExceptionReasonPhrase(
            final String message
    ) {
        this.message = message;
    }

    @Override
    public String getKey() {
        return this.name();
    }

    @Override
    public String getDefaultMessage() {
        return this.message;
    }
}
