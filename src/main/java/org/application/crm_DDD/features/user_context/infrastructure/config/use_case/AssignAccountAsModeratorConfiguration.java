package org.application.crm_DDD.features.user_context.infrastructure.config.use_case;

import org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_moderator.AssignAccountAsModeratorRepositoryPort;
import org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_moderator.AssignAccountAsModeratorService;
import org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_moderator.AssignAccountAsModeratorUseCase;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.assign_account_as_moderator.AssignAccountAsModeratorDecorator;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.assign_account_as_moderator.AssignAccountAsModeratorRepository;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.assign_account_as_moderator.AssignAccountAsModeratorRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssignAccountAsModeratorConfiguration {

    @Bean
    public AssignAccountAsModeratorRepositoryPort assignAccountAsModeratorRepositoryPort(
            final AssignAccountAsModeratorRepository assignAccountAsModeratorRepository
    ) {
        return new AssignAccountAsModeratorRepositoryAdapter(assignAccountAsModeratorRepository);
    }

    @Bean
    public AssignAccountAsModeratorUseCase assignAccountAsModeratorUseCase(
            final AssignAccountAsModeratorRepositoryPort assignAccountAsModeratorRepositoryPort
    ) {
        var assignAccountAsModeratorService = new AssignAccountAsModeratorService(assignAccountAsModeratorRepositoryPort);

        return new AssignAccountAsModeratorDecorator(assignAccountAsModeratorService);
    }

}
