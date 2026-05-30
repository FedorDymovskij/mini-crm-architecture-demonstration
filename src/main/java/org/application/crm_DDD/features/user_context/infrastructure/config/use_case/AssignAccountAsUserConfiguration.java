package org.application.crm_DDD.features.user_context.infrastructure.config.use_case;

import org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_user.AssignAccountAsUserRepositoryPort;
import org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_user.AssignAccountAsUserService;
import org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_user.AssignAccountAsUserUseCase;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.assign_account_as_user.AssignAccountAsUserDecorator;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.assign_account_as_user.AssignAccountAsUserRepository;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.assign_account_as_user.AssignAccountAsUserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssignAccountAsUserConfiguration {

    @Bean
    public AssignAccountAsUserRepositoryPort assignAccountAsUserRepositoryPort(
            final AssignAccountAsUserRepository assignAccountAsUserRepository
    ) {
        return new AssignAccountAsUserRepositoryAdapter(assignAccountAsUserRepository);
    }

    @Bean
    public AssignAccountAsUserUseCase assignAccountAsUserUseCase(
            final AssignAccountAsUserRepositoryPort assignAccountAsUserRepositoryPort
    ) {
        var assignAccountAsUserService = new AssignAccountAsUserService(assignAccountAsUserRepositoryPort);

        return new AssignAccountAsUserDecorator(assignAccountAsUserService);
    }

}
