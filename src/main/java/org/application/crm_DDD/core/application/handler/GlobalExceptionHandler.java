package org.application.crm_DDD.core.application.handler;

import org.application.crm_DDD.core.application.exception.*;
import org.application.crm_DDD.core.application.web.exception_mapper.CoreExceptionMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final List<ReasonPhraseHttpStatusCodeMapper> mappers;

    public GlobalExceptionHandler(final List<ReasonPhraseHttpStatusCodeMapper> mappers) {
        this.mappers = mappers;
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<CoreExceptionDTO> handleDomainException(final DomainException exception) {
        ExceptionReasonPhraseSpecification spec = exception.getErrorSpecification();

        HttpStatus status = this.mappers.stream()
                .filter(mapper -> mapper.supports(spec))
                .map(mapper -> mapper.mapToStatus(spec))
                .findFirst()
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);

        var exceptionDTO = new CoreExceptionDTO(
                status,
                spec.getKey(),
                exception.getMessage(),
                null
        );

        return ResponseEntity.status(status).body(exceptionDTO);
    }

    @ExceptionHandler(InfrastructureException.class)
    public ResponseEntity<CoreExceptionDTO> handleInfrastructureException(final InfrastructureException exception) {
        ExceptionReasonPhraseSpecification spec = exception.getErrorSpecification();

        HttpStatus status = this.mappers.stream()
                .filter(mapper -> mapper.supports(spec))
                .map(mapper -> mapper.mapToStatus(spec))
                .findFirst()
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);

        var exceptionDTO = new CoreExceptionDTO(
                status,
                spec.getKey(),
                exception.getMessage(),
                null
        );

        return ResponseEntity.status(status).body(exceptionDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CoreExceptionDTO> handleValidationErrors(final MethodArgumentNotValidException exception) {
        return this.createSystemResponse(CoreExceptionReasonPhrase.VALIDATION_ERROR, exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CoreExceptionDTO> handleReadableException(final HttpMessageNotReadableException exception) {
        return this.createSystemResponse(CoreExceptionReasonPhrase.DESERIALIZATION_ERROR, exception.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<CoreExceptionDTO> globalExceptionHandler(final Exception exception) {
//        return this.createSystemResponse(CoreExceptionReasonPhrase.INTERNAL_SERVER_ERROR, exception.getMessage());
//    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CoreExceptionDTO> handleTypeMismatchException(
            final MethodArgumentTypeMismatchException exception
    ) {
        return this.createSystemResponse(CoreExceptionReasonPhrase.DESERIALIZATION_ERROR, exception.getMessage());
    }

    private ResponseEntity<CoreExceptionDTO> createSystemResponse(
            final CoreExceptionReasonPhrase reasonPhrase,
            final String debugMessage
    ) {
        HttpStatus status = CoreExceptionMapper.getHttpStatus(reasonPhrase);
        var exceptionDTO = new CoreExceptionDTO(
                status,
                reasonPhrase.getKey(),
                reasonPhrase.getDefaultMessage(),
                null
        );
        return ResponseEntity.status(status).body(exceptionDTO);
    }
}