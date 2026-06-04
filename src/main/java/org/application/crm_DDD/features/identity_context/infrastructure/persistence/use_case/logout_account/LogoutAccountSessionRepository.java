package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.logout_account;

import org.application.crm_DDD.features.identity_context.infrastructure.persistence.entity.IdentityContextSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LogoutAccountSessionRepository extends JpaRepository<IdentityContextSessionEntity, UUID> {

    Optional<IdentityContextSessionEntity> findById(final UUID sessionId);

}
