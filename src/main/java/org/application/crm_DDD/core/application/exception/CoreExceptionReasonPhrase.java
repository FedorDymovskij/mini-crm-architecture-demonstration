package org.application.crm_DDD.core.application.exception;

public enum CoreExceptionReasonPhrase implements ExceptionReasonPhraseSpecification {
    INTERNAL_SERVER_ERROR("Internal server error"),
    VALIDATION_ERROR("Validation error"),
    DESERIALIZATION_ERROR("Deserialization error");

    private final String message;

    CoreExceptionReasonPhrase(final String message) {
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
