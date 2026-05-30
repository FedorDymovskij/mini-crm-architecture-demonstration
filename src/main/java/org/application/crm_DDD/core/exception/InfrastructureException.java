package org.application.crm_DDD.core.exception;

public class InfrastructureException extends RuntimeException {
    private final ExceptionReasonPhraseSpecification errorSpecification;

    public InfrastructureException(
            final ExceptionReasonPhraseSpecification errorSpecification,
            final String message
    ) {
        super(message != null ? message : errorSpecification.getDefaultMessage());
        this.errorSpecification = errorSpecification;
    }

    public ExceptionReasonPhraseSpecification getErrorSpecification() {
        return this.errorSpecification;
    }
}
