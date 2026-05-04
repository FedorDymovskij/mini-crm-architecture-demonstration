package org.application.securitydemonstration.security.exception.handler;

import org.application.securitydemonstration.security.exception.AccountException;
import org.application.securitydemonstration.security.exception.DTO.BasicExceptionDTO;
import org.application.securitydemonstration.security.exception.code.AccountExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AccountExceptionHandler {

    @ExceptionHandler(AccountException.NotFound.class)
    public ResponseEntity<BasicExceptionDTO> handleAccountNotFoundException(
            final AccountException.NotFound exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BasicExceptionDTO(
                        false,
                        HttpStatus.BAD_REQUEST.value(),
                        AccountExceptionCode.ACCOUNT_NOT_FOUND.name(),
                        exception.getMessage(),
                        null,
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler(AccountException.AlreadyExists.class)
    public ResponseEntity<BasicExceptionDTO> handleAccountAlreadyExistsException(
            final AccountException.AlreadyExists exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BasicExceptionDTO(
                        false,
                        HttpStatus.BAD_REQUEST.value(),
                        AccountExceptionCode.ACCOUNT_ALREADY_EXISTS.name(),
                        exception.getMessage(),
                        null,
                        LocalDateTime.now()
                ));
    }
}
