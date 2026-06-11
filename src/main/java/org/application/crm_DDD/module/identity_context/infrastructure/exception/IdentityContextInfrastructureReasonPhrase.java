package org.application.crm_DDD.module.identity_context.infrastructure.exception;

import org.application.crm_DDD.core.application.exception.ExceptionReasonPhraseSpecification;

public enum IdentityContextInfrastructureReasonPhrase implements ExceptionReasonPhraseSpecification {
    USER_AUTHENTICATION_NOT_FOUND("Authentication is not founded locally"),
    ;

    private final String defaultMessage;

    IdentityContextInfrastructureReasonPhrase(final String defaultMessage) {
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
