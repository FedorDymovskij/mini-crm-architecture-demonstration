package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.unblock_account;

import org.application.crm_DDD.features.identity_context.infrastructure.persistence.entity.IdentityContextAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UnblockAccountRepository extends JpaRepository<IdentityContextAccountEntity, UUID> {
}
