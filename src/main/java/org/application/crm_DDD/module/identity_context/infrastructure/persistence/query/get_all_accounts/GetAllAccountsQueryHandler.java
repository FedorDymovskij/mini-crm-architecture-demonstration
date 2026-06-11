package org.application.crm_DDD.module.identity_context.infrastructure.persistence.query.get_all_accounts;

import org.application.crm_DDD.module.identity_context.application.query.get_all_accounts.GetAllAccountsDTO;
import org.application.crm_DDD.module.identity_context.application.query.get_all_accounts.GetAllAccountsFinder;
import org.application.crm_DDD.module.identity_context.application.query.get_all_accounts.GetAllAccountsQuery;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GetAllAccountsQueryHandler implements GetAllAccountsFinder {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<GetAllAccountsDTO> mapper = new GetAllAccountsRowMapper();

    @Override
    public List<GetAllAccountsDTO> execute(final GetAllAccountsQuery query) {

        long limit = 10;
        long offset = 0;

        if (query.page() != null && query.size() != null) {
            long page = query.page();
            long size = query.size();

            limit = size;
            offset = (page - 1) * size;
        }

        Map<String, Object> params = Map.of(
                "limit", limit,
                "offset", offset
        );

        String sql = "SELECT id, username, role, status FROM accounts LIMIT :limit OFFSET :offset";

        return this.jdbcTemplate.query(
                sql,
                params,
                this.mapper
        );
    }

    public GetAllAccountsQueryHandler(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
