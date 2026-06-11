package org.application.crm_DDD.module.identity_context.infrastructure.persistence.jpa;

import org.application.crm_DDD.module.identity_context.infrastructure.persistence.entity.IdentityContextAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaAccountRepository extends JpaRepository<IdentityContextAccountEntity, UUID> {

    boolean existsByUsername(final String username);

    Optional<IdentityContextAccountEntity> findByUsername(final String username);

    Optional<IdentityContextAccountEntity> findById(final UUID id);

}
