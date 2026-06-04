package org.application.crm_DDD.features.identity_context.infrastructure.persistence.query.get_account_by_id;

import org.application.crm_DDD.features.identity_context.application.query.get_account_by_id.GetAccountByIdDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class GetAccountByIdRowMapper implements RowMapper<GetAccountByIdDTO> {
    @Override
    public GetAccountByIdDTO mapRow(
            final ResultSet rs,
            final int rowNum
    ) throws SQLException {
        return new GetAccountByIdDTO(
                rs.getObject("id", UUID.class),
                rs.getString("username"),
                rs.getString("role"),
                rs.getString("status")
        );
    }
}
