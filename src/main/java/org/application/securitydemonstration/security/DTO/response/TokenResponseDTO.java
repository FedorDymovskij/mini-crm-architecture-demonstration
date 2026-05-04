package org.application.securitydemonstration.security.DTO.response;

import java.util.UUID;

public class TokenResponseDTO {

    public record Full(
            UUID refreshToken,
            String accessToken
    ) {

    }

}
