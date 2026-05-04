package org.application.securitydemonstration.security.exception.handler;

import org.application.securitydemonstration.security.exception.AccountException;
import org.application.securitydemonstration.security.exception.AuthException;
import org.application.securitydemonstration.security.exception.DTO.BasicExceptionDTO;
import org.application.securitydemonstration.security.exception.code.AccountExceptionCode;
import org.application.securitydemonstration.security.exception.code.AuthExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(AuthException.AuthenticationFailed.class)
    public ResponseEntity<BasicExceptionDTO> handleAuthenticationFailedException(
            final AuthException.AuthenticationFailed exception
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new BasicExceptionDTO(
                        false,
                        HttpStatus.BAD_REQUEST.value(),
                        AuthExceptionCode.AUTHENTICATION_FAILED.name(),
                        exception.getMessage(),
                        null,
                        LocalDateTime.now()
                ));
    }
}
