package org.application.securitydemonstration.security.exception.handler;

import io.jsonwebtoken.JwtException;
import org.application.securitydemonstration.security.exception.DTO.BasicExceptionDTO;
import org.application.securitydemonstration.security.exception.TokenException;
import org.application.securitydemonstration.security.exception.code.TokenExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class TokenExceptionHandler {

    @ExceptionHandler(TokenException.RefreshTokenNotFound.class)
    public ResponseEntity<BasicExceptionDTO> handleRefreshTokenNotFoundException(
            final TokenException.RefreshTokenNotFound exception
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new BasicExceptionDTO(
                        false,
                        HttpStatus.UNAUTHORIZED.value(),
                        TokenExceptionCode.REFRESH_TOKEN_NOT_FOUND.name(),
                        exception.getMessage(),
                        null,
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler(TokenException.RefreshTokenExpired.class)
    public ResponseEntity<BasicExceptionDTO> handleRefreshTokenExpiredException(
            final TokenException.RefreshTokenExpired exception
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new BasicExceptionDTO(
                        false,
                        HttpStatus.UNAUTHORIZED.value(),
                        TokenExceptionCode.REFRESH_TOKEN_EXPIRED.name(),
                        exception.getMessage(),
                        null,
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler(TokenException.RefreshTokenRevoked.class)
    public ResponseEntity<BasicExceptionDTO> handleRefreshTokenRevokedException(
            final TokenException.RefreshTokenRevoked exception
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new BasicExceptionDTO(
                        false,
                        HttpStatus.UNAUTHORIZED.value(),
                        TokenExceptionCode.REFRESH_TOKEN_REVOKED.name(),
                        exception.getMessage(),
                        null,
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler(TokenException.RefreshTokenRefreshed.class)
    public ResponseEntity<BasicExceptionDTO> handleRefreshTokenRefreshedException(
            final TokenException.RefreshTokenRefreshed exception
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new BasicExceptionDTO(
                        false,
                        HttpStatus.UNAUTHORIZED.value(),
                        TokenExceptionCode.REFRESH_TOKEN_REFRESHED.name(),
                        exception.getMessage(),
                        null,
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler(TokenException.CookieRefreshTokenNotFound.class)
    public ResponseEntity<BasicExceptionDTO> handleCookieRefreshTokenNotFoundException(
            final TokenException.CookieRefreshTokenNotFound exception
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new BasicExceptionDTO(
                        false,
                        HttpStatus.UNAUTHORIZED.value(),
                        TokenExceptionCode.COOKIE_REFRESH_TOKEN_NOT_FOUND.name(),
                        exception.getMessage(),
                        null,
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler({
            JwtException.class,
    })
    public ResponseEntity<BasicExceptionDTO> handleJwtException(
            final JwtException jwtException
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new BasicExceptionDTO(
                        false,
                        HttpStatus.UNAUTHORIZED.value(),
                        TokenExceptionCode.INVALID_ACCESS_TOKEN.name(),
                        jwtException.getMessage(),
                        null,
                        LocalDateTime.now()
                ));
    }

}
