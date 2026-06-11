package org.application.crm_DDD.module.identity_context.infrastructure.web.exception_mapper;

import org.application.crm_DDD.core.application.exception.ExceptionReasonPhraseSpecification;
import org.application.crm_DDD.core.application.exception.ReasonPhraseHttpStatusCodeMapper;
import org.application.crm_DDD.module.identity_context.infrastructure.exception.IdentityContextInfrastructureReasonPhrase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IdentityContextInfrastructureExceptionMapper implements ReasonPhraseHttpStatusCodeMapper {
    private static final Map<IdentityContextInfrastructureReasonPhrase, HttpStatus> MAP = Map.of(
            IdentityContextInfrastructureReasonPhrase.USER_AUTHENTICATION_NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR
    );

    @Override
    public boolean supports(final ExceptionReasonPhraseSpecification specification) {
        return specification instanceof IdentityContextInfrastructureReasonPhrase;
    }

    @Override
    public HttpStatus mapToStatus(final ExceptionReasonPhraseSpecification specification) {
        return MAP.getOrDefault((IdentityContextInfrastructureReasonPhrase) specification, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
