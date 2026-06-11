package org.application.crm_DDD.module.identity_context.application.query.get_account_by_id;

import java.util.Optional;

public interface GetAccountByIdFinder {
    Optional<GetAccountByIdDTO> execute(final GetAccountByIdQuery query);
}
