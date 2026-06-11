package org.application.crm_DDD.module.identity_context.infrastructure.configuration;

import org.application.crm_DDD.module.identity_context.application.command.assign_account_as_moderator.AssignAccountAsModeratorService;
import org.application.crm_DDD.module.identity_context.application.command.assign_account_as_user.AssignAccountAsUserService;
import org.application.crm_DDD.module.identity_context.application.command.assign_account_as_user.AssignAccountAsUserUseCase;
import org.application.crm_DDD.module.identity_context.application.command.block_account.BlockAccountService;
import org.application.crm_DDD.module.identity_context.application.command.block_account.BlockAccountUseCase;
import org.application.crm_DDD.module.identity_context.application.command.login_account.LoginAccountService;
import org.application.crm_DDD.module.identity_context.application.command.login_account.LoginAccountUseCase;
import org.application.crm_DDD.module.identity_context.application.command.logout_account.LogoutAccountService;
import org.application.crm_DDD.module.identity_context.application.command.logout_account.LogoutAccountUseCase;
import org.application.crm_DDD.module.identity_context.application.command.refresh_session.RefreshSessionService;
import org.application.crm_DDD.module.identity_context.application.command.refresh_session.RefreshSessionUseCase;
import org.application.crm_DDD.module.identity_context.application.command.register_account.RegisterAccountService;
import org.application.crm_DDD.module.identity_context.application.command.register_account.RegisterAccountUseCase;
import org.application.crm_DDD.module.identity_context.application.command.unblock_account.UnblockAccountService;
import org.application.crm_DDD.module.identity_context.application.command.unblock_account.UnblockAccountUseCase;
import org.application.crm_DDD.module.identity_context.infrastructure.persistence.repository.AccountRepositoryAdapter;
import org.application.crm_DDD.module.identity_context.infrastructure.persistence.repository.SessionRepositoryAdapter;
import org.application.crm_DDD.module.identity_context.infrastructure.security.PasswordEncoderAdapter;
import org.application.crm_DDD.module.identity_context.infrastructure.security.TokenGeneratorAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentityContextConfiguration {

    @Bean
    public RegisterAccountUseCase registerAccountUseCase(
            final AccountRepositoryAdapter accountRepository,
            final PasswordEncoderAdapter passwordEncoder
    ) {
        return new RegisterAccountService(accountRepository, passwordEncoder);
    }

    @Bean
    public LoginAccountUseCase loginAccountUseCase(
            final AccountRepositoryAdapter accountRepository,
            final SessionRepositoryAdapter sessionRepository,
            final PasswordEncoderAdapter passwordEncoder,
            final TokenGeneratorAdapter tokenGenerator
    ) {
        return new LoginAccountService(accountRepository, sessionRepository, passwordEncoder, tokenGenerator);
    }

    @Bean
    public RefreshSessionUseCase refreshSessionUseCase(
            final AccountRepositoryAdapter accountRepository,
            final SessionRepositoryAdapter sessionRepository,
            final TokenGeneratorAdapter tokenGenerator
    ) {
        return new RefreshSessionService(accountRepository, sessionRepository, tokenGenerator);
    }

    @Bean
    public LogoutAccountUseCase logoutAccountUseCase(
            final SessionRepositoryAdapter sessionRepository
    ) {
        return new LogoutAccountService(sessionRepository);
    }

    @Bean
    public BlockAccountUseCase blockAccountUseCase(
            final AccountRepositoryAdapter accountRepository
    ) {
        return new BlockAccountService(accountRepository);
    }

    @Bean
    public UnblockAccountUseCase unblockAccountUseCase(
            final AccountRepositoryAdapter accountRepository
    ) {
        return new UnblockAccountService(accountRepository);
    }

    @Bean
    public AssignAccountAsUserUseCase assignAccountAsUserUseCase(
            final AccountRepositoryAdapter accountRepository
    ) {
        return new AssignAccountAsUserService(accountRepository);
    }

    @Bean
    public AssignAccountAsModeratorService assignAccountAsModeratorService(
            final AccountRepositoryAdapter accountRepository
    ) {
        return new AssignAccountAsModeratorService(accountRepository);
    }

}
