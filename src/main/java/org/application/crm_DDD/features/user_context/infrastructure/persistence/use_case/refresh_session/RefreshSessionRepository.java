package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.refresh_session;

import org.application.crm_DDD.features.user_context.infrastructure.persistence.entity.UserContextSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshSessionRepository extends JpaRepository<UserContextSessionEntity, UUID> {


}
