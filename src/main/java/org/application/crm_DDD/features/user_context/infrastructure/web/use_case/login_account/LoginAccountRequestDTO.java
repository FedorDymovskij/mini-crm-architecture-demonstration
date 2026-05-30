package org.application.crm_DDD.features.user_context.infrastructure.web.use_case.login_account;

import jakarta.validation.constraints.NotBlank;

public record LoginAccountRequestDTO(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
