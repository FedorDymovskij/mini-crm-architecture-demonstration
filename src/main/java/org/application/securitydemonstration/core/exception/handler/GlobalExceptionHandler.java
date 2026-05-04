package org.application.securitydemonstration.core.exception.handler;

import org.application.securitydemonstration.security.exception.DTO.BasicExceptionDTO;
import org.application.securitydemonstration.security.exception.code.BasicExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BasicExceptionDTO> handleValidationErrors(
            final MethodArgumentNotValidException exception
    ) {
        List<Map<String, String>> details = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> Map.of(
                        "field", error.getField(),
                        "message", error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid value"
                ))
                .collect(Collectors.toList());

        return buildResponse(HttpStatus.BAD_REQUEST,
                BasicExceptionCode.VALIDATION_ERROR.name(),
                "Validation error",
                details
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BasicExceptionDTO> handleReadableException(
            final HttpMessageNotReadableException exception
    ) {
        return buildResponse(HttpStatus.BAD_REQUEST,
                BasicExceptionCode.MALFORMED_JSON.name(),
                "JSON deserialization error",
                exception.getLocalizedMessage());
    }

    private ResponseEntity<BasicExceptionDTO> buildResponse(
            final HttpStatus status,
            final String code,
            final String message,
            final Object details
    ) {
        BasicExceptionDTO response = new BasicExceptionDTO(
                false,
                status.value(),
                code,
                message,
                details,
                LocalDateTime.now()

        );
        return new ResponseEntity<>(response, status);
    }
}
