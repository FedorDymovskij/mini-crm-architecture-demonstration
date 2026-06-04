package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.refresh_session;

import org.application.crm_DDD.features.identity_context.infrastructure.persistence.entity.IdentityContextSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RefreshSessionRepository extends JpaRepository<IdentityContextSessionEntity, UUID> {


}
