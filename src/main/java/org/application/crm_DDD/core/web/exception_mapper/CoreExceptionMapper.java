package org.application.crm_DDD.core.web.exception_mapper;

import org.application.crm_DDD.core.exception.CoreExceptionReasonPhrase;
import org.application.crm_DDD.core.exception.ExceptionReasonPhraseSpecification;
import org.application.crm_DDD.core.exception.ReasonPhraseHttpStatusCodeMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CoreExceptionMapper implements ReasonPhraseHttpStatusCodeMapper {
    private final static Map<CoreExceptionReasonPhrase, HttpStatus> MAP = Map.of(
            CoreExceptionReasonPhrase.DESERIALIZATION_ERROR, HttpStatus.BAD_REQUEST,
            CoreExceptionReasonPhrase.VALIDATION_ERROR, HttpStatus.BAD_REQUEST,
            CoreExceptionReasonPhrase.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR
    );

    @Override
    public boolean supports(
            final ExceptionReasonPhraseSpecification specification
    ) {
        return specification instanceof CoreExceptionReasonPhrase;
    }

    @Override
    public HttpStatus mapToStatus(
            final ExceptionReasonPhraseSpecification specification
    ) {
        return MAP.getOrDefault(specification, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static HttpStatus getHttpStatus(
            ExceptionReasonPhraseSpecification specification
    ) {
        return MAP.getOrDefault(specification, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
