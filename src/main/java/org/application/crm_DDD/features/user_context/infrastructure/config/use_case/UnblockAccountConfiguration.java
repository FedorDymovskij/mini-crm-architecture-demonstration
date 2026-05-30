package org.application.crm_DDD.features.user_context.infrastructure.config.use_case;

import org.application.crm_DDD.features.user_context.application.use_case.unblock_account.UnblockAccountRepositoryPort;
import org.application.crm_DDD.features.user_context.application.use_case.unblock_account.UnblockAccountService;
import org.application.crm_DDD.features.user_context.application.use_case.unblock_account.UnblockAccountUseCase;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.unblock_account.UnblockAccountRepository;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.unblock_account.UnblockAccountRepositoryAdapter;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.unblock_account.UnblockAccountUseCaseDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnblockAccountConfiguration {
    @Bean
    public UnblockAccountRepositoryPort unblockAccountRepositoryPort(final UnblockAccountRepository unblockAccountRepository) {
        return new UnblockAccountRepositoryAdapter(unblockAccountRepository);
    }

    @Bean
    public UnblockAccountUseCase unblockAccountUseCase(final UnblockAccountRepositoryPort unblockAccountRepositoryPort) {
        var unblockAccountService = new UnblockAccountService(unblockAccountRepositoryPort);

        return new UnblockAccountUseCaseDecorator(unblockAccountService);
    }
}
