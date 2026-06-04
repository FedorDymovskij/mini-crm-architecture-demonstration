package org.application.crm_DDD.features.identity_context.infrastructure.web.exception_mapper;

import org.application.crm_DDD.core.application.exception.ExceptionReasonPhraseSpecification;
import org.application.crm_DDD.core.application.exception.ReasonPhraseHttpStatusCodeMapper;
import org.application.crm_DDD.features.identity_context.infrastructure.exception.UserContextInfrastructureReasonPhrase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserContextInfrastructureExceptionMapper implements ReasonPhraseHttpStatusCodeMapper {
    private static final Map<UserContextInfrastructureReasonPhrase, HttpStatus> MAP = Map.of(
            UserContextInfrastructureReasonPhrase.USER_AUTHENTICATION_NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR
    );

    @Override
    public boolean supports(final ExceptionReasonPhraseSpecification specification) {
        return specification instanceof UserContextInfrastructureReasonPhrase;
    }

    @Override
    public HttpStatus mapToStatus(final ExceptionReasonPhraseSpecification specification) {
        return MAP.getOrDefault((UserContextInfrastructureReasonPhrase) specification, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
