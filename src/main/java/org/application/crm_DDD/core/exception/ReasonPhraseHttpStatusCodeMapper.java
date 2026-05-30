package org.application.crm_DDD.core.exception;

import org.springframework.http.HttpStatus;

public interface ReasonPhraseHttpStatusCodeMapper {
    boolean supports(final ExceptionReasonPhraseSpecification specification);

    HttpStatus mapToStatus(final ExceptionReasonPhraseSpecification specification);

}
