package org.application.crm_DDD.module.identity_context.application.query.get_account_by_id;

import java.util.UUID;

public record GetAccountByIdDTO(
        UUID accountId,
        String username,
        String role,
        String status
) {
}
