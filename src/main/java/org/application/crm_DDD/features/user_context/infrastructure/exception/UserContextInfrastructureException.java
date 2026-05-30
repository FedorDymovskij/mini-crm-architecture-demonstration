package org.application.crm_DDD.features.user_context.infrastructure.exception;

public class UserContextInfrastructureException extends org.application.crm_DDD.core.exception.InfrastructureException {
    public UserContextInfrastructureException(
            final String message,
            final UserContextInfrastructureReasonPhrase reasonPhrase
    ) {
        super(reasonPhrase, message);
    }
}
