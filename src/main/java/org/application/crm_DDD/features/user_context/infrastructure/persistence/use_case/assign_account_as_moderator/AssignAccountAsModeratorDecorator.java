package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.assign_account_as_moderator;

import org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_moderator.AssignAccountAsModeratorCommand;
import org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_moderator.AssignAccountAsModeratorService;
import org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_moderator.AssignAccountAsModeratorUseCase;
import org.springframework.transaction.annotation.Transactional;

public class AssignAccountAsModeratorDecorator implements AssignAccountAsModeratorUseCase {
    private final AssignAccountAsModeratorService assignAccountAsModeratorService;

    @Override
    @Transactional
    public void execute(final AssignAccountAsModeratorCommand command) {
        this.assignAccountAsModeratorService.execute(command);
    }

    public AssignAccountAsModeratorDecorator(AssignAccountAsModeratorService assignAccountAsModeratorService) {
        this.assignAccountAsModeratorService = assignAccountAsModeratorService;
    }
}
