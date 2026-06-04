package org.application.crm_DDD.features.identity_context.infrastructure.persistence.query.get_all_accounts;

import org.application.crm_DDD.features.identity_context.application.query.get_all_accounts.GetAllAccountsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class GetAllAccountsRowMapper implements RowMapper<GetAllAccountsDTO> {
    @Override
    public GetAllAccountsDTO mapRow(
            final ResultSet rs,
            final int rowNum
    ) throws SQLException {
        return new GetAllAccountsDTO(
                rs.getObject("id", UUID.class),
                rs.getString("username"),
                rs.getString("role"),
                rs.getString("status")
        );
    }
}
