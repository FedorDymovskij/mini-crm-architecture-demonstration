package org.application.crm_DDD.module.identity_context.infrastructure.web.command.block_account;

import org.application.crm_DDD.core.security.util.CoreSecurityUtils;
import org.application.crm_DDD.module.identity_context.application.command.block_account.BlockAccountUseCase;
import org.application.crm_DDD.module.identity_context.infrastructure.exception.IdentityContextInfrastructureException;
import org.application.crm_DDD.module.identity_context.infrastructure.exception.IdentityContextInfrastructureReasonPhrase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class BlockAccountController {
    private final BlockAccountUseCase blockAccountUseCase;
    private final CoreSecurityUtils coreSecurityUtils;

    @PostMapping("/accounts/{uuid}/block")
    public ResponseEntity<Void> blockAccount(
            final @PathVariable(name = "uuid") UUID targetAccountId
    ) {
        UUID initiatorAccountId = this.coreSecurityUtils.getAuthenticatedAccountId()
                .orElseThrow(() -> new IdentityContextInfrastructureException("Initiator account not found", IdentityContextInfrastructureReasonPhrase.USER_AUTHENTICATION_NOT_FOUND));


        this.blockAccountUseCase.execute(initiatorAccountId, targetAccountId);

        return ResponseEntity.ok().build();
    }

    public BlockAccountController(BlockAccountUseCase blockAccountUseCase, CoreSecurityUtils coreSecurityUtils) {
        this.blockAccountUseCase = blockAccountUseCase;
        this.coreSecurityUtils = coreSecurityUtils;
    }
}
