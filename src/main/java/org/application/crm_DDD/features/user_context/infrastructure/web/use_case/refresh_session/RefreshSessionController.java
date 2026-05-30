package org.application.crm_DDD.features.user_context.infrastructure.web.use_case.refresh_session;

import org.application.crm_DDD.features.user_context.application.use_case.refresh_session.RefreshSessionCommand;
import org.application.crm_DDD.features.user_context.application.use_case.refresh_session.RefreshSessionResult;
import org.application.crm_DDD.features.user_context.application.use_case.refresh_session.RefreshSessionUseCase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RefreshSessionController {
    private final RefreshSessionUseCase refreshSessionUseCase;

    @PostMapping("/refresh")
    public ResponseEntity<RefreshSessionResponseDTO> refreshSession(
            @CookieValue(name = "refresh_token", required = false) String refreshTokenToRefresh
    ) {
        if (refreshTokenToRefresh.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var command = new RefreshSessionCommand(UUID.fromString(refreshTokenToRefresh));

        RefreshSessionResult result = this.refreshSessionUseCase.execute(command);
        String refreshToken = result.refreshToken().toString();
        String accessToken = result.accessToken();
        long expirationMinutes = result.expirationMinutes();

        if (refreshToken.isEmpty() || accessToken.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ResponseCookie newRefreshTokenCookie = ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .maxAge(expirationMinutes * 60)
                .path("/")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, newRefreshTokenCookie.toString())
                .body(new RefreshSessionResponseDTO(accessToken));
    }

    public RefreshSessionController(RefreshSessionUseCase refreshSessionUseCase) {
        this.refreshSessionUseCase = refreshSessionUseCase;
    }
}
