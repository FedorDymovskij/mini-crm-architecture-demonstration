package org.application.crm_DDD.features.user_context.infrastructure.web.exception_mapper;

import org.application.crm_DDD.core.exception.ExceptionReasonPhraseSpecification;
import org.application.crm_DDD.core.exception.ReasonPhraseHttpStatusCodeMapper;
import org.application.crm_DDD.features.user_context.domain.exception.UserContextExceptionReasonPhrase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserContextDomainExceptionMapper implements ReasonPhraseHttpStatusCodeMapper {
    private static final Map<UserContextExceptionReasonPhrase, HttpStatus> MAP = Map.of(
            UserContextExceptionReasonPhrase.USER_UNAUTHORIZED, HttpStatus.UNAUTHORIZED,
            UserContextExceptionReasonPhrase.USER_FORBIDDEN, HttpStatus.FORBIDDEN,
            UserContextExceptionReasonPhrase.USER_BAD_REQUEST, HttpStatus.BAD_REQUEST,
            UserContextExceptionReasonPhrase.USER_NOT_FOUND, HttpStatus.NOT_FOUND,
            UserContextExceptionReasonPhrase.USER_ALREADY_EXISTS, HttpStatus.CONFLICT,
            UserContextExceptionReasonPhrase.USER_SESSION_NOT_FOUND, HttpStatus.CONFLICT,
            UserContextExceptionReasonPhrase.USER_SESSION_EXPIRED, HttpStatus.CONFLICT,
            UserContextExceptionReasonPhrase.USER_SESSION_REVOKED, HttpStatus.CONFLICT,
            UserContextExceptionReasonPhrase.USER_SESSION_REFRESHED, HttpStatus.CONFLICT,
            UserContextExceptionReasonPhrase.USER_DATA_VALIDATION_FAILED, HttpStatus.BAD_REQUEST
    );


    @Override
    public boolean supports(
            final ExceptionReasonPhraseSpecification specification
    ) {
        return specification instanceof UserContextExceptionReasonPhrase;
    }

    @Override
    public HttpStatus mapToStatus(final ExceptionReasonPhraseSpecification specification) {
        return MAP.getOrDefault((UserContextExceptionReasonPhrase) specification, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
