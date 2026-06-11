package org.application.crm_DDD.module.identity_context.infrastructure.persistence.jpa;

import org.application.crm_DDD.module.identity_context.infrastructure.persistence.entity.IdentityContextSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaSessionRepository extends JpaRepository<IdentityContextSessionEntity, UUID> {
}
