package org.application.securitydemonstration.security.repository;

import org.application.securitydemonstration.security.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByUsername(String username);

    boolean existsByUsername(String username);

}
