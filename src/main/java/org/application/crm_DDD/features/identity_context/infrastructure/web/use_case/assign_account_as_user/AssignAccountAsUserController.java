package org.application.crm_DDD.features.identity_context.infrastructure.web.use_case.assign_account_as_user;

import org.application.crm_DDD.core.security.util.CoreSecurityUtils;
import org.application.crm_DDD.features.identity_context.application.use_case.assign_account_as_user.AssignAccountAsUserUseCase;
import org.application.crm_DDD.features.identity_context.infrastructure.exception.UserContextInfrastructureException;
import org.application.crm_DDD.features.identity_context.infrastructure.exception.UserContextInfrastructureReasonPhrase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AssignAccountAsUserController {
    private final AssignAccountAsUserUseCase assignAccountAsUserUseCase;
    private final CoreSecurityUtils coreSecurityUtils;

    @PostMapping("/accounts/{id}/assign-as-user")
    public ResponseEntity<Void> assignAccountAsUser(
            final @PathVariable(name = "id") UUID targetAccountId
    ) {
        UUID initiatorAccountId = this.coreSecurityUtils.getAuthenticatedAccountId()
                .orElseThrow(() -> new UserContextInfrastructureException("Initiator account not found", UserContextInfrastructureReasonPhrase.USER_AUTHENTICATION_NOT_FOUND));

        this.assignAccountAsUserUseCase.execute(initiatorAccountId, targetAccountId);

        return ResponseEntity.ok().build();
    }

    public AssignAccountAsUserController(AssignAccountAsUserUseCase assignAccountAsUserUseCase, CoreSecurityUtils coreSecurityUtils) {
        this.assignAccountAsUserUseCase = assignAccountAsUserUseCase;
        this.coreSecurityUtils = coreSecurityUtils;
    }
}
