package org.application.securitydemonstration.security.service;

import jakarta.transaction.Transactional;
import org.application.securitydemonstration.security.DTO.request.AccountRequestDTO;
import org.application.securitydemonstration.security.DTO.response.AccountResponseDTO;
import org.application.securitydemonstration.security.DTO.response.TokenResponseDTO;
import org.application.securitydemonstration.security.exception.AccountException;
import org.application.securitydemonstration.security.exception.AuthException;
import org.application.securitydemonstration.security.model.entity.AccountEntity;
import org.application.securitydemonstration.security.model.enums.AccountRole;
import org.application.securitydemonstration.security.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.accountRepository.findByUsername(username)
                .orElseThrow(() -> new AccountException.NotFound(username));
    }

    @Transactional
    public void register(
            final AccountRequestDTO.Register request
    ) {
        if (this.accountRepository.existsByUsername(request.username())) {
            throw new AccountException.AlreadyExists(request.username());
        }
        var accountEntityToCreate = new AccountEntity();
        accountEntityToCreate.setUsername(request.username());
        accountEntityToCreate.setPassword(this.passwordEncoder.encode(request.password()));
        accountEntityToCreate.setRole(AccountRole.USER);

        this.accountRepository.save(accountEntityToCreate);
    }

    @Transactional
    public AccountResponseDTO.Login login(
            final AccountRequestDTO.Login request
    ) {
        var accountEntityToLogin = this.accountRepository.findByUsername(request.username())
                .orElseThrow(() -> new AuthException.AuthenticationFailed());

        if (!this.passwordEncoder.matches(request.password(), accountEntityToLogin.getPassword())) {
            throw new AuthException.AuthenticationFailed();
        }

        TokenResponseDTO.Full tokens = this.tokenService.createRefreshTokenAndGetAccessToken(accountEntityToLogin.getId(), accountEntityToLogin.getRole());

        return new AccountResponseDTO.Login(tokens.refreshToken(), tokens.accessToken());
    }

    @Transactional
    public TokenResponseDTO.Full refresh(final UUID refreshTokenUUID) {
        return this.tokenService.refreshRefreshTokenAndGetAccessToken(refreshTokenUUID);
    }

    public AuthService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }


}
