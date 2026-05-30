package org.application.crm_DDD.core.security.exception;

import org.application.crm_DDD.core.exception.ExceptionReasonPhraseSpecification;

public enum SecurityExceptionReasonPhrase implements ExceptionReasonPhraseSpecification {
    SECURITY_INVALID_ACCESS_TOKEN("Access token expired");

    private final String defaultMessage;

    SecurityExceptionReasonPhrase(final String defaultMessage) {
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
