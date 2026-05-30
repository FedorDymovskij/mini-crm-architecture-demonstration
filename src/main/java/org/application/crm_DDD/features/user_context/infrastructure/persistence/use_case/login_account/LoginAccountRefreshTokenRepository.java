package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.login_account;

import org.application.crm_DDD.features.user_context.infrastructure.persistence.entity.UserContextSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginAccountRefreshTokenRepository extends JpaRepository<UserContextSessionEntity, UUID> {
}
