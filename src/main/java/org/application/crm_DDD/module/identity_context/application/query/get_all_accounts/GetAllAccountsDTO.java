package org.application.crm_DDD.module.identity_context.application.query.get_all_accounts;

import java.util.UUID;

public record GetAllAccountsDTO(
        UUID accountId,
        String username,
        String role,
        String status
) {
}
