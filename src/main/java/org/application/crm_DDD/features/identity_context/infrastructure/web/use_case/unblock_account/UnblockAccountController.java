package org.application.crm_DDD.features.identity_context.infrastructure.web.use_case.unblock_account;

import org.application.crm_DDD.core.security.util.CoreSecurityUtils;
import org.application.crm_DDD.features.identity_context.application.use_case.unblock_account.UnblockAccountUseCase;
import org.application.crm_DDD.features.identity_context.infrastructure.exception.UserContextInfrastructureException;
import org.application.crm_DDD.features.identity_context.infrastructure.exception.UserContextInfrastructureReasonPhrase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UnblockAccountController {
    private final UnblockAccountUseCase unblockAccountUseCase;
    private final CoreSecurityUtils coreSecurityUtils;

    @PostMapping("/accounts/{uuid}/unblock")
    public ResponseEntity<Void> unblockAccount(
            final @PathVariable(name = "uuid") UUID targetAccountId
    ) {
        UUID initiatorAccountId = this.coreSecurityUtils.getAuthenticatedAccountId()
                .orElseThrow(() -> new UserContextInfrastructureException("Initiator account not found", UserContextInfrastructureReasonPhrase.USER_AUTHENTICATION_NOT_FOUND));

        this.unblockAccountUseCase.execute(initiatorAccountId, targetAccountId);

        return ResponseEntity.ok().build();
    }

    public UnblockAccountController(UnblockAccountUseCase unblockAccountUseCase, CoreSecurityUtils coreSecurityUtils) {
        this.unblockAccountUseCase = unblockAccountUseCase;
        this.coreSecurityUtils = coreSecurityUtils;
    }
}
