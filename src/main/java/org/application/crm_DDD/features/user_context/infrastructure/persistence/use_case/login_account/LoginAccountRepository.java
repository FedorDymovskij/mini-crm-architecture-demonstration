package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.login_account;

import org.application.crm_DDD.features.user_context.infrastructure.persistence.entity.UserContextAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoginAccountRepository extends JpaRepository<UserContextAccountEntity, UUID> {
    Optional<UserContextAccountEntity> findByUsername(final String username);
}
