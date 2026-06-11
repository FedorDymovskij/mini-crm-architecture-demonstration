package org.application.crm_DDD.module.identity_context.infrastructure.web.command.logout_account;

import org.application.crm_DDD.module.identity_context.application.command.logout_account.LogoutAccountResult;
import org.application.crm_DDD.module.identity_context.application.command.logout_account.LogoutAccountUseCase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class LogoutAccountController {

    private final LogoutAccountUseCase logoutAccountUseCase;

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutAccountController(
            final @CookieValue(name = "refresh_token") UUID sessionId
    ) {

        LogoutAccountResult result = this.logoutAccountUseCase.execute(sessionId);

        ResponseCookie cookie = ResponseCookie.from("refresh_token", sessionId.toString())
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .maxAge(0)
                .path("/")
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();

    }

    public LogoutAccountController(LogoutAccountUseCase logoutAccountUseCase) {
        this.logoutAccountUseCase = logoutAccountUseCase;
    }
}
