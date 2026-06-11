package org.application.crm_DDD.module.identity_context.infrastructure.web.exception_mapper;

import org.application.crm_DDD.core.application.exception.ExceptionReasonPhraseSpecification;
import org.application.crm_DDD.core.application.exception.ReasonPhraseHttpStatusCodeMapper;
import org.application.crm_DDD.module.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IdentityContextDomainExceptionMapper implements ReasonPhraseHttpStatusCodeMapper {
    private static final Map<IdentityContextExceptionReasonPhrase, HttpStatus> MAP = Map.of(
            IdentityContextExceptionReasonPhrase.USER_UNAUTHORIZED, HttpStatus.UNAUTHORIZED,
            IdentityContextExceptionReasonPhrase.USER_FORBIDDEN, HttpStatus.FORBIDDEN,
            IdentityContextExceptionReasonPhrase.ACCOUNT_BAD_REQUEST, HttpStatus.BAD_REQUEST,
            IdentityContextExceptionReasonPhrase.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND,
            IdentityContextExceptionReasonPhrase.ACCOUNT_ALREADY_EXISTS, HttpStatus.CONFLICT,
            IdentityContextExceptionReasonPhrase.USER_SESSION_NOT_FOUND, HttpStatus.CONFLICT,
            IdentityContextExceptionReasonPhrase.USER_SESSION_EXPIRED, HttpStatus.CONFLICT,
            IdentityContextExceptionReasonPhrase.USER_SESSION_REVOKED, HttpStatus.CONFLICT,
            IdentityContextExceptionReasonPhrase.USER_SESSION_REFRESHED, HttpStatus.CONFLICT,
            IdentityContextExceptionReasonPhrase.USER_DATA_VALIDATION_FAILED, HttpStatus.BAD_REQUEST
    );


    @Override
    public boolean supports(
            final ExceptionReasonPhraseSpecification specification
    ) {
        return specification instanceof IdentityContextExceptionReasonPhrase;
    }

    @Override
    public HttpStatus mapToStatus(final ExceptionReasonPhraseSpecification specification) {
        return MAP.getOrDefault((IdentityContextExceptionReasonPhrase) specification, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
