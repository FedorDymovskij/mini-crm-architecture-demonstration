package org.application.crm_DDD.features.user_context.infrastructure.config.use_case;

import org.application.crm_DDD.features.user_context.application.use_case.block_account.BlockAccountRepositoryPort;
import org.application.crm_DDD.features.user_context.application.use_case.block_account.BlockAccountService;
import org.application.crm_DDD.features.user_context.application.use_case.block_account.BlockAccountUseCase;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.block_account.BlockAccountRepository;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.block_account.BlockAccountRepositoryAdapter;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.block_account.BlockAccountUseCaseDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlockAccountConfiguration {
    @Bean
    public BlockAccountRepositoryPort blockAccountRepositoryPort(
            final BlockAccountRepository blockAccountRepository
    ) {
        return new BlockAccountRepositoryAdapter(blockAccountRepository);
    }

    @Bean
    public BlockAccountUseCase blockAccountUseCase(final BlockAccountRepositoryPort blockAccountRepositoryPort) {

        var blockAccountService = new BlockAccountService(blockAccountRepositoryPort);

        return new BlockAccountUseCaseDecorator(blockAccountService);
    }
}
