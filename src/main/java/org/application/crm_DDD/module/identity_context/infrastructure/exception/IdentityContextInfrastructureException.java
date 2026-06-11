package org.application.crm_DDD.module.identity_context.infrastructure.exception;

import org.application.crm_DDD.core.application.exception.InfrastructureException;

public class IdentityContextInfrastructureException extends InfrastructureException {
    public IdentityContextInfrastructureException(
            final String message,
            final IdentityContextInfrastructureReasonPhrase reasonPhrase
    ) {
        super(reasonPhrase, message);
    }
}
