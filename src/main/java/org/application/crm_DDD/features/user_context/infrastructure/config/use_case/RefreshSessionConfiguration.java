package org.application.crm_DDD.features.user_context.infrastructure.config.use_case;

import org.application.crm_DDD.features.user_context.application.use_case.refresh_session.*;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.refresh_session.*;
import org.application.crm_DDD.features.user_context.infrastructure.security.provider.AccessTokenGeneratorProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RefreshSessionConfiguration {
    @Bean
    public RefreshSessionRepositoryPort refreshSessionRepositoryPort(
            final RefreshSessionRepository refreshSessionRepository
    ) {
        return new RefreshSessionRepositoryAdapter(refreshSessionRepository);
    }

    @Bean
    public RefreshSessionAccountRepositoryPort refreshSessionAccountRepositoryPort(
            final RefreshSessionAccountRepository refreshSessionAccountRepository
    ) {
        return new RefreshSessionAccountRepositoryAdapter(refreshSessionAccountRepository);
    }

    @Bean
    public RefreshSessionTokenGeneratorPort refreshSessionTokenGeneratorPort(
            final AccessTokenGeneratorProvider accessTokenGeneratorProvider
    ) {
        return new RefreshSessionTokenGeneratorAdapter(accessTokenGeneratorProvider);
    }

    @Bean
    public RefreshSessionUseCase refreshSessionUseCase(
            final RefreshSessionTokenGeneratorPort refreshSessionTokenGeneratorPort,
            final RefreshSessionAccountRepositoryPort refreshSessionAccountRepositoryPort,
            final RefreshSessionRepositoryPort refreshSessionRepositoryPort
    ) {
        var refreshSessionService = new RefreshSessionService(
                refreshSessionRepositoryPort,
                refreshSessionAccountRepositoryPort,
                refreshSessionTokenGeneratorPort);

        return new RefreshSessionUseCaseDecorator(refreshSessionService);
    }
}
