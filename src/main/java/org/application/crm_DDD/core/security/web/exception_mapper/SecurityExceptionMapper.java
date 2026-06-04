package org.application.crm_DDD.core.security.web.exception_mapper;

import org.application.crm_DDD.core.application.exception.ExceptionReasonPhraseSpecification;
import org.application.crm_DDD.core.application.exception.ReasonPhraseHttpStatusCodeMapper;
import org.application.crm_DDD.core.security.exception.SecurityExceptionReasonPhrase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SecurityExceptionMapper implements ReasonPhraseHttpStatusCodeMapper {
    private static final Map<SecurityExceptionReasonPhrase, HttpStatus> MAP = Map.of(
            SecurityExceptionReasonPhrase.SECURITY_INVALID_ACCESS_TOKEN, HttpStatus.BAD_REQUEST
    );

    @Override
    public boolean supports(
            final ExceptionReasonPhraseSpecification specification
    ) {
        return specification instanceof SecurityExceptionReasonPhrase;
    }

    @Override
    public HttpStatus mapToStatus(
            final ExceptionReasonPhraseSpecification specification
    ) {
        return MAP.getOrDefault((SecurityExceptionReasonPhrase) specification, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
