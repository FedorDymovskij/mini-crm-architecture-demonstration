package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.assign_account_as_user;

import org.application.crm_DDD.features.user_context.infrastructure.persistence.entity.UserContextAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssignAccountAsUserRepository extends JpaRepository<UserContextAccountEntity, UUID> {
}
