package org.application.crm_DDD.core.exception;

public abstract class DomainException extends RuntimeException {
    private final ExceptionReasonPhraseSpecification errorSpecification;

    public DomainException(
            final ExceptionReasonPhraseSpecification errorSpecification,
            final String customMessage
    ) {
        super(customMessage != null ? customMessage : errorSpecification.getDefaultMessage());
        this.errorSpecification = errorSpecification;
    }

    public ExceptionReasonPhraseSpecification getErrorSpecification() {
        return errorSpecification;
    }
}
