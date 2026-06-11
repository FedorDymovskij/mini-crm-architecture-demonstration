package org.application.crm_DDD.module.identity_context.infrastructure.web.query.get_account_by_id;

import org.application.crm_DDD.module.identity_context.application.query.get_account_by_id.GetAccountByIdDTO;
import org.application.crm_DDD.module.identity_context.application.query.get_account_by_id.GetAccountByIdFinder;
import org.application.crm_DDD.module.identity_context.application.query.get_account_by_id.GetAccountByIdQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetAccountByIdController {
    private final GetAccountByIdFinder getAccountByIdFinder;

    @GetMapping("/accounts/{id}")
    public ResponseEntity<GetAccountByIdDTO> getAccountByIdFinder(
            final @PathVariable(name = "id") UUID accountId
    ) {
        var query = new GetAccountByIdQuery(accountId);

        var result = this.getAccountByIdFinder.execute(query);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result.get());
    }

    public GetAccountByIdController(GetAccountByIdFinder getAccountByIdFinder) {
        this.getAccountByIdFinder = getAccountByIdFinder;
    }
}
