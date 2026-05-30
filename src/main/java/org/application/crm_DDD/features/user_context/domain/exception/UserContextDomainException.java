package org.application.crm_DDD.features.user_context.domain.exception;

import org.application.crm_DDD.core.exception.DomainException;

public class UserContextDomainException extends DomainException {

    public UserContextDomainException(
            final String message,
            final UserContextExceptionReasonPhrase reasonPhrase
    ) {
        super(reasonPhrase, message);
    }
}
