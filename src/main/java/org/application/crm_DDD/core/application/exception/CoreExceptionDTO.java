package org.application.crm_DDD.core.application.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public record CoreExceptionDTO(
        HttpStatus httpStatusCode,
        String httpReasonPhrase,
        String message,
        Map<Object, Object> details
) {
}
