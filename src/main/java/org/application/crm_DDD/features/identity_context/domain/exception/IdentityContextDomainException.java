package org.application.crm_DDD.features.identity_context.domain.exception;

import org.application.crm_DDD.core.application.exception.DomainException;

public class IdentityContextDomainException extends DomainException {

    public IdentityContextDomainException(
            final String message,
            final IdentityContextExceptionReasonPhrase reasonPhrase
    ) {
        super(reasonPhrase, message);
    }
}
