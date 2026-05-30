package org.application.crm_DDD.features.user_context.infrastructure.web.use_case.login_account;

import jakarta.validation.Valid;
import org.application.crm_DDD.features.user_context.application.use_case.login_user.LoginAccountCommand;
import org.application.crm_DDD.features.user_context.application.use_case.login_user.LoginAccountResult;
import org.application.crm_DDD.features.user_context.application.use_case.login_user.LoginAccountUseCase;
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
            final @RequestBody @Valid LoginAccountRequestDTO request
    ) {
        var command = new LoginAccountCommand(request.username(), request.password());

        LoginAccountResult result = this.loginAccountUseCase.execute(command);

        String refreshToken = result.refreshToken().toString();
        String accessToken = result.accessToken();

        if (accessToken.isEmpty() || refreshToken.isEmpty()) {
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
