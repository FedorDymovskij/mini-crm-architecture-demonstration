package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.register_user;

import org.application.crm_DDD.features.user_context.infrastructure.persistence.entity.UserContextAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegisterUserRepository extends JpaRepository<UserContextAccountEntity, UUID> {
    boolean existsByUsername(final String username);
}
