package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.assign_account_as_user;

import org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_user.AssignAccountAsUserCommand;
import org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_user.AssignAccountAsUserService;
import org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_user.AssignAccountAsUserUseCase;
import org.springframework.transaction.annotation.Transactional;

public class AssignAccountAsUserDecorator implements AssignAccountAsUserUseCase {
    private final AssignAccountAsUserService assignAccountAsUserService;

    @Override
    @Transactional
    public void execute(final AssignAccountAsUserCommand command) {
        this.assignAccountAsUserService.execute(command);
    }

    public AssignAccountAsUserDecorator(AssignAccountAsUserService assignAccountAsUserService) {
        this.assignAccountAsUserService = assignAccountAsUserService;
    }
}
