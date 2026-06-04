package org.application.crm_DDD.features.identity_context.infrastructure.web.query.get_all_accounts;

import org.application.crm_DDD.features.identity_context.application.query.get_all_accounts.GetAllAccountsDTO;
import org.application.crm_DDD.features.identity_context.application.query.get_all_accounts.GetAllAccountsFinder;
import org.application.crm_DDD.features.identity_context.application.query.get_all_accounts.GetAllAccountsQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetAllAccountsController {

    private final GetAllAccountsFinder allAccountsFinder;

    @GetMapping("/accounts")
    public ResponseEntity<List<GetAllAccountsDTO>> getAllAccounts(
            final @RequestParam(name = "page", required = false) Long page,
            final @RequestParam(name = "size", required = false) Long size
    ) {

        var query = new GetAllAccountsQuery(
                page,
                size
        );

        return ResponseEntity.ok(this.allAccountsFinder.execute(query));
    }

    public GetAllAccountsController(GetAllAccountsFinder allAccountsFinder) {
        this.allAccountsFinder = allAccountsFinder;
    }
}
