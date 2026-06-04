package org.application.crm_DDD.core.security.exception;

import org.application.crm_DDD.core.application.exception.InfrastructureException;

public class SecurityException extends InfrastructureException {
    public SecurityException(
            final String message,
            final SecurityExceptionReasonPhrase reasonPhrase
    ) {

        super(reasonPhrase, message);
    }
}
