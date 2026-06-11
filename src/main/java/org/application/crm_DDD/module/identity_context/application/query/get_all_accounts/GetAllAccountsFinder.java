package org.application.crm_DDD.module.identity_context.application.query.get_all_accounts;

import java.util.List;

public interface GetAllAccountsFinder {
    List<GetAllAccountsDTO> execute(final GetAllAccountsQuery query);
}
