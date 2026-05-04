package org.application.securitydemonstration.security.DTO.response;

import java.util.UUID;

public class AccountResponseDTO {

    public record Login(
            UUID refreshToken,
            String accessToken
    ) {
    }

    public record AccessToken(String accessToken) {
    }
}
