package org.application.crm_DDD.features.identity_context.infrastructure.exception;

import org.application.crm_DDD.core.application.exception.InfrastructureException;

public class UserContextInfrastructureException extends InfrastructureException {
    public UserContextInfrastructureException(
            final String message,
            final UserContextInfrastructureReasonPhrase reasonPhrase
    ) {
        super(reasonPhrase, message);
    }
}
