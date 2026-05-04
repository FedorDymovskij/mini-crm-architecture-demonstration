package org.application.securitydemonstration.security.exception.DTO;

import java.time.LocalDateTime;

public record BasicExceptionDTO(
        boolean success,
        int status,
        String code,
        String message,
        Object details,
        LocalDateTime timestamp
) {
}

