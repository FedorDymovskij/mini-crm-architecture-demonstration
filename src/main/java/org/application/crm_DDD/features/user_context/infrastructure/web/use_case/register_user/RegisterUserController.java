package org.application.crm_DDD.features.user_context.infrastructure.web.use_case.register_user;

import jakarta.validation.Valid;
import org.application.crm_DDD.features.user_context.application.use_case.register_user.RegisterUserCommand;
import org.application.crm_DDD.features.user_context.application.use_case.register_user.RegisterUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterUserController {
    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(
            final @RequestBody @Valid RegisterUserDTO request
    ) {
        var command = new RegisterUserCommand(
                request.username(),
                request.password()
        );
        this.registerUserUseCase.execute(command);
        return ResponseEntity.ok().build();
    }

    public RegisterUserController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }
}
