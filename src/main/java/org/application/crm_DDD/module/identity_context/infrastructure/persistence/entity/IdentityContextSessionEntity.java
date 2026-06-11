package org.application.crm_DDD.module.identity_context.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens")
public class IdentityContextSessionEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "status")
    private String status;

    public IdentityContextSessionEntity(UUID id, UUID accountId, LocalDateTime expiresAt, String status) {
        this.expiresAt = expiresAt;
        this.accountId = accountId;
        this.id = id;
        this.status = status;
    }

    public IdentityContextSessionEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID account_id) {
        this.accountId = account_id;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
