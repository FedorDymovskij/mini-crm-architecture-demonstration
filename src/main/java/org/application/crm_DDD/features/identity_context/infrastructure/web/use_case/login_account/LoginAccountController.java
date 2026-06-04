package org.application.crm_DDD.features.identity_context.infrastructure.web.use_case.login_account;

import org.application.crm_DDD.features.identity_context.application.use_case.login_account.LoginAccountResult;
import org.application.crm_DDD.features.identity_context.application.use_case.login_account.LoginAccountUseCase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginAccountController {
    private final LoginAccountUseCase loginAccountUseCase;

    @PostMapping("/login")
    public ResponseEntity<LoginAccountResponseDTO> login(
            final @RequestBody LoginAccountRequestDTO request
    ) {

        LoginAccountResult result = this.loginAccountUseCase.execute(request.username(), request.password());

        String refreshToken = result.refreshToken().toString();
        String accessToken = result.accessToken();

        if (accessToken.isEmpty() || refreshToken == null) {
            return ResponseEntity.badRequest().build();
        }
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .maxAge(result.expirationMinutes() * 60)
                .path("/")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(new LoginAccountResponseDTO(accessToken));


    }

    public LoginAccountController(LoginAccountUseCase loginAccountUseCase) {
        this.loginAccountUseCase = loginAccountUseCase;
    }
}
