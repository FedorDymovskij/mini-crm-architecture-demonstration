package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.login_account;

import org.application.crm_DDD.features.identity_context.infrastructure.persistence.entity.IdentityContextSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginAccountSessionRepository extends JpaRepository<IdentityContextSessionEntity, UUID> {
}
