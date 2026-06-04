package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.login_account;

import org.application.crm_DDD.features.identity_context.application.use_case.login_account.LoginAccountRepositoryPort;
import org.application.crm_DDD.features.identity_context.domain.model.account.Account;
import org.application.crm_DDD.features.identity_context.domain.model.account.Username;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginAccountRepositoryAdapter implements LoginAccountRepositoryPort {
    private final LoginAccountRepository loginAccountRepository;

    @Override
    public Optional<Account> findByUsername(final Username username) {
        return loginAccountRepository.findByUsername(username.value())
                .map(accountEntity ->
                        Account.fromSnapshot(new Account.Snapshot(
                                accountEntity.getId(),
                                accountEntity.getUsername(),
                                accountEntity.getPassword(),
                                accountEntity.getRole(),
                                accountEntity.getStatus()
                        ))
                );
    }

    public LoginAccountRepositoryAdapter(LoginAccountRepository loginAccountRepository) {
        this.loginAccountRepository = loginAccountRepository;
    }
}

