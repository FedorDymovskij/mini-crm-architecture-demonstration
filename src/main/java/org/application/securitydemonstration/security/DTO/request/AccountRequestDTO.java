package org.application.securitydemonstration.security.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AccountRequestDTO {

    public record Register(
            @NotNull(message = "Username can't be null")
            @Size(min = 4, max = 254, message = "Username must be 4-254 symbols")
            String username,
            @NotNull(message = "Password can't be null")
            @Size(min = 4, max = 254, message = "Password must be 4-254 symbols")
            String password
    ) {
    }

    public record Login(
            @NotNull(message = "Username can't be null")
            String username,
            @NotNull(message = "Password can't be null")
            String password
    ) {
    }

}
