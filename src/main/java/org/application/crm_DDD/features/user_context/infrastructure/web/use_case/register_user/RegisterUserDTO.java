package org.application.crm_DDD.features.user_context.infrastructure.web.use_case.register_user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserDTO(
        @NotBlank
        @Size(min = 6, max = 32)
        String username,
        @NotBlank
        @Size(min = 6, max = 32)
        String password
) {

}
