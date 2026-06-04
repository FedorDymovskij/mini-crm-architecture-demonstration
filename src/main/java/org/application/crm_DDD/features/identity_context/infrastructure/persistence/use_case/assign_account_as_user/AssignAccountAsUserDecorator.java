package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.assign_account_as_user;

import jakarta.transaction.Transactional;
import org.application.crm_DDD.features.identity_context.application.use_case.assign_account_as_user.AssignAccountAsUserRepositoryPort;
import org.application.crm_DDD.features.identity_context.application.use_case.assign_account_as_user.AssignAccountAsUserService;
import org.application.crm_DDD.features.identity_context.application.use_case.assign_account_as_user.AssignAccountAsUserUseCase;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AssignAccountAsUserDecorator implements AssignAccountAsUserUseCase {
    private final AssignAccountAsUserService assignAccountAsUserService;

    @Override
    @Transactional
    public void execute(
            final UUID initiatorAccountId,
            final UUID targetAccountId
    ) {
        this.assignAccountAsUserService.execute(initiatorAccountId, targetAccountId);
    }

    public AssignAccountAsUserDecorator(
            final AssignAccountAsUserRepositoryPort assignAccountAsUserRepositoryPort
    ) {
        this.assignAccountAsUserService = new AssignAccountAsUserService(assignAccountAsUserRepositoryPort);
    }
}
