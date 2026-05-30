package org.application.crm_DDD.features.user_context.infrastructure.web.use_case.block_account;

import org.application.crm_DDD.core.security.util.CoreSecurityUtils;
import org.application.crm_DDD.features.user_context.application.use_case.block_account.BlockAccountCommand;
import org.application.crm_DDD.features.user_context.application.use_case.block_account.BlockAccountUseCase;
import org.application.crm_DDD.features.user_context.infrastructure.exception.UserContextInfrastructureException;
import org.application.crm_DDD.features.user_context.infrastructure.exception.UserContextInfrastructureReasonPhrase;
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
                .orElseThrow(() -> new UserContextInfrastructureException("Initiator account not found", UserContextInfrastructureReasonPhrase.USER_AUTHENTICATION_NOT_FOUND));

        var command = new BlockAccountCommand(initiatorAccountId, targetAccountId);

        this.blockAccountUseCase.execute(command);

        return ResponseEntity.ok().build();
    }

    public BlockAccountController(BlockAccountUseCase blockAccountUseCase, CoreSecurityUtils coreSecurityUtils) {
        this.blockAccountUseCase = blockAccountUseCase;
        this.coreSecurityUtils = coreSecurityUtils;
    }
}
