package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.assign_account_as_moderator;

import org.application.crm_DDD.features.identity_context.application.use_case.assign_account_as_moderator.AssignAccountAsModeratorRepositoryPort;
import org.application.crm_DDD.features.identity_context.application.use_case.assign_account_as_moderator.AssignAccountAsModeratorService;
import org.application.crm_DDD.features.identity_context.application.use_case.assign_account_as_moderator.AssignAccountAsModeratorUseCase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class AssignAccountAsModeratorDecorator implements AssignAccountAsModeratorUseCase {
    private final AssignAccountAsModeratorService assignAccountAsModeratorService;

    @Override
    @Transactional
    public void execute(
            final UUID initiatorAccountId,
            final UUID targetAccountId
    ) {
        this.assignAccountAsModeratorService.execute(
                initiatorAccountId,
                targetAccountId
        );
    }

    public AssignAccountAsModeratorDecorator(
            final AssignAccountAsModeratorRepositoryPort assignAccountAsModeratorRepositoryPort
    ) {
        this.assignAccountAsModeratorService = new AssignAccountAsModeratorService(assignAccountAsModeratorRepositoryPort);
    }
}
