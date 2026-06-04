package org.application.crm_DDD.features.identity_context.infrastructure.exception;

import org.application.crm_DDD.core.application.exception.ExceptionReasonPhraseSpecification;

public enum UserContextInfrastructureReasonPhrase implements ExceptionReasonPhraseSpecification {
    USER_AUTHENTICATION_NOT_FOUND("Authentication is not founded locally"),
    ;

    private final String defaultMessage;

    UserContextInfrastructureReasonPhrase(final String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getKey() {
        return this.name();
    }

    @Override
    public String getDefaultMessage() {
        return this.defaultMessage;
    }
}
