package org.application.securitydemonstration.security.service;

import jakarta.transaction.Transactional;
import org.application.securitydemonstration.security.DTO.response.TokenResponseDTO;
import org.application.securitydemonstration.security.exception.AccountException;
import org.application.securitydemonstration.security.exception.TokenException;
import org.application.securitydemonstration.security.model.entity.RefreshTokenEntity;
import org.application.securitydemonstration.security.model.enums.AccountRole;
import org.application.securitydemonstration.security.model.enums.RefreshTokenStatus;
import org.application.securitydemonstration.security.repository.AccountRepository;
import org.application.securitydemonstration.security.repository.TokenRepository;
import org.application.securitydemonstration.security.util.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final AccountRepository accountRepository;
    private final JwtUtils jwtUtils;

    @Value("${app.security.refresh-token.expiration-ms}")
    private int refreshTokenExpirationMs;

    @Transactional
    public TokenResponseDTO.Full createRefreshTokenAndGetAccessToken(
            final Long accountId,
            final AccountRole role
    ) {
        UUID uuid = UUID.randomUUID();

        var accountEntity = this.accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException.NotFound(accountId));

        var refreshTokenEntityToCreate = new RefreshTokenEntity();
        refreshTokenEntityToCreate.setTokenUUID(uuid);
        refreshTokenEntityToCreate.setAccountEntity(accountEntity);
        refreshTokenEntityToCreate.setExpiresAt(LocalDateTime.now().plusSeconds(refreshTokenExpirationMs / 1000));
        refreshTokenEntityToCreate.setStatus(RefreshTokenStatus.ACTIVE);

        var refreshTokenEntity = this.tokenRepository.save(refreshTokenEntityToCreate);

        return new TokenResponseDTO.Full(
                refreshTokenEntity.getTokenUUID(),
                this.jwtUtils.generateJwtToken(
                        accountEntity.getId(),
                        accountEntity.getUsername(),
                        accountEntity.getRole()
                )
        );
    }

    @Transactional
    public RefreshTokenEntity getAndValidateRefreshTokenEntityByUUID(final UUID refreshTokenUUID) {
        var refreshTokenEntity = this.tokenRepository.findByTokenUUID(refreshTokenUUID)
                .orElseThrow(() -> new TokenException.RefreshTokenNotFound(refreshTokenUUID));

        var accountEntity = refreshTokenEntity.getAccountEntity();

        if (refreshTokenEntity.getStatus() == RefreshTokenStatus.REVOKED) {
            throw new TokenException.RefreshTokenRevoked(refreshTokenUUID);
        }

        if (refreshTokenEntity.getExpiresAt().isBefore(LocalDateTime.now())) {
            refreshTokenEntity.setStatus(RefreshTokenStatus.EXPIRED);
            this.tokenRepository.save(refreshTokenEntity);
            throw new TokenException.RefreshTokenExpired(refreshTokenUUID);
        }

        if (refreshTokenEntity.getStatus() == RefreshTokenStatus.REFRESHED) {
            this.tokenRepository.modifyAllRefreshTokensByAccountEntityId(
                    RefreshTokenStatus.REVOKED,
                    accountEntity.getId()
            );
            throw new TokenException.RefreshTokenRefreshed(refreshTokenUUID);
        }

        // TODO: Schedule scripts to set all expired refresh tokens as EXPIRED

        return refreshTokenEntity;
    }

    @Transactional
    public TokenResponseDTO.Full refreshRefreshTokenAndGetAccessToken(final UUID refreshTokenUUID) {
        var refreshTokenEntityToRefresh = this.getAndValidateRefreshTokenEntityByUUID(refreshTokenUUID);

        var accountEntity = refreshTokenEntityToRefresh.getAccountEntity();

        refreshTokenEntityToRefresh.setStatus(RefreshTokenStatus.REFRESHED);

        this.tokenRepository.save(refreshTokenEntityToRefresh);

        var refreshTokenEntityToCreate = new RefreshTokenEntity();
        refreshTokenEntityToCreate.setTokenUUID(UUID.randomUUID());
        refreshTokenEntityToCreate.setAccountEntity(accountEntity);
        refreshTokenEntityToCreate.setExpiresAt(LocalDateTime.now().plusSeconds(this.refreshTokenExpirationMs / 1000));
        refreshTokenEntityToCreate.setStatus(RefreshTokenStatus.ACTIVE);

        var createdRefreshTokenEntity = this.tokenRepository.save(refreshTokenEntityToCreate);

        return new TokenResponseDTO.Full(
                createdRefreshTokenEntity.getTokenUUID(),
                this.jwtUtils.generateJwtToken(
                        accountEntity.getId(),
                        accountEntity.getUsername(),
                        accountEntity.getRole()
                )
        );
    }


    public TokenService(TokenRepository tokenRepository, AccountRepository accountRepository, JwtUtils jwtUtils) {
        this.tokenRepository = tokenRepository;
        this.accountRepository = accountRepository;
        this.jwtUtils = jwtUtils;
    }
}
