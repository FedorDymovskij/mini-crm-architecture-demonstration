package org.application.crm_DDD.features.identity_context.infrastructure.web.use_case.register_account;

import org.application.crm_DDD.features.identity_context.application.use_case.register_account.RegisterAccountUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterAccountController {
    private final RegisterAccountUseCase registerUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(
            final @RequestBody RegisterAccountDTO request
    ) {
        String username = request.username();
        String password = request.password();

        this.registerUserUseCase.execute(username, password);
        return ResponseEntity.ok().build();
    }

    public RegisterAccountController(RegisterAccountUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }
}
