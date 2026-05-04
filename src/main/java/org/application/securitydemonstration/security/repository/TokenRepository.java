package org.application.securitydemonstration.security.repository;

import jakarta.transaction.Transactional;
import org.application.securitydemonstration.security.model.entity.RefreshTokenEntity;
import org.application.securitydemonstration.security.model.enums.RefreshTokenStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    @EntityGraph(attributePaths = {"accountEntity"})
    Optional<RefreshTokenEntity> findByTokenUUID(final UUID TokenUUID);


    @Modifying
    @Transactional
    @Query("UPDATE RefreshTokenEntity rte SET rte.status = :newStatus WHERE rte.accountEntity.id = :accountEntityId")
    Integer modifyAllRefreshTokensByAccountEntityId(
            final @Param("newStatus") RefreshTokenStatus newStatus,
            final @Param("accountEntityId") Long accountEntityId
    );

}
