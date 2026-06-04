package org.application.crm_DDD.features.identity_context.infrastructure.persistence.query.get_account_by_id;

import org.application.crm_DDD.features.identity_context.application.query.get_account_by_id.GetAccountByIdDTO;
import org.application.crm_DDD.features.identity_context.application.query.get_account_by_id.GetAccountByIdFinder;
import org.application.crm_DDD.features.identity_context.application.query.get_account_by_id.GetAccountByIdQuery;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class GetAccountByIdQueryHandler implements GetAccountByIdFinder {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final GetAccountByIdRowMapper rowMapper = new GetAccountByIdRowMapper();

    @Override
    public Optional<GetAccountByIdDTO> execute(final GetAccountByIdQuery query) {


        if (query.accountId() == null) {
            return Optional.empty();
        }

        String sql = "SELECT id, username, role, status FROM accounts WHERE id = :id";

        Map<String, Object> params = Map.of("id", query.accountId());

        return this.jdbcTemplate
                .query(sql, params, this.rowMapper)
                .stream()
                .findAny();
    }

    public GetAccountByIdQueryHandler(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
