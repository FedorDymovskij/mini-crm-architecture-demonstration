package org.application.crm_DDD.features.identity_context.infrastructure.web.use_case.assign_account_as_moderator;

import org.application.crm_DDD.core.security.util.CoreSecurityUtils;
import org.application.crm_DDD.features.identity_context.application.use_case.assign_account_as_moderator.AssignAccountAsModeratorUseCase;
import org.application.crm_DDD.features.identity_context.infrastructure.exception.UserContextInfrastructureException;
import org.application.crm_DDD.features.identity_context.infrastructure.exception.UserContextInfrastructureReasonPhrase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AssignAccountAsModeratorController {
    private final CoreSecurityUtils coreSecurityUtils;
    private final AssignAccountAsModeratorUseCase assignAccountAsModeratorUseCase;

    @PostMapping("/accounts/{id}/assign-as-moderator")
    public ResponseEntity<Void> assignAccountAsModerator(
            final @PathVariable(name = "id") UUID targetAccountId
    ) {
        UUID initiatorAccountId = this.coreSecurityUtils.getAuthenticatedAccountId()
                .orElseThrow(() -> new UserContextInfrastructureException("Initiator account not found", UserContextInfrastructureReasonPhrase.USER_AUTHENTICATION_NOT_FOUND));


        this.assignAccountAsModeratorUseCase.execute(initiatorAccountId, targetAccountId);

        return ResponseEntity.ok().build();
    }

    public AssignAccountAsModeratorController(CoreSecurityUtils coreSecurityUtils, AssignAccountAsModeratorUseCase assignAccountAsModeratorUseCase) {
        this.coreSecurityUtils = coreSecurityUtils;
        this.assignAccountAsModeratorUseCase = assignAccountAsModeratorUseCase;
    }
}
