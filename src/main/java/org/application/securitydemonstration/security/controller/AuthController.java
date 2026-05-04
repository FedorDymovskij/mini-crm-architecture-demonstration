package org.application.securitydemonstration.security.controller;

import jakarta.validation.Valid;
import org.application.securitydemonstration.security.DTO.request.AccountRequestDTO;
import org.application.securitydemonstration.security.DTO.response.AccountResponseDTO;
import org.application.securitydemonstration.security.DTO.response.TokenResponseDTO;
import org.application.securitydemonstration.security.exception.TokenException;
import org.application.securitydemonstration.security.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;
    @Value("${app.security.refresh-token.expiration-ms}")
    private int refreshTokenExpirationMs;

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            final @RequestBody @Valid AccountRequestDTO.Register request
    ) {
        this.authService.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AccountResponseDTO.AccessToken> login(
            final @RequestBody @Valid AccountRequestDTO.Login request
    ) {
        AccountResponseDTO.Login response = this.authService.login(request);

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", response.refreshToken().toString())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(this.refreshTokenExpirationMs)
                .sameSite("Strict")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(new AccountResponseDTO.AccessToken(response.accessToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AccountResponseDTO.AccessToken> refreshAccessToken(
            final @CookieValue(name = "refresh_token") String stringRefreshToken
    ) {
        if (stringRefreshToken == null || stringRefreshToken.isEmpty()) {
            throw new TokenException.CookieRefreshTokenNotFound();
        }

        TokenResponseDTO.Full tokens = this.authService.refresh(UUID.fromString(stringRefreshToken));
        String refreshedAccessToken = tokens.accessToken();
        UUID refreshedRefreshToken = tokens.refreshToken();

        ResponseCookie refreshedRefreshTokenCookie = ResponseCookie.from("refresh_token", refreshedRefreshToken.toString())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(this.refreshTokenExpirationMs)
                .sameSite("Strict")
                .build();


        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshedRefreshTokenCookie.toString())
                .body(new AccountResponseDTO.AccessToken(refreshedAccessToken));
    }

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
}
