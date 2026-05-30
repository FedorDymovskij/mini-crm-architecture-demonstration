package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.login_account;

import org.application.crm_DDD.features.user_context.application.use_case.login_user.LoginAccountRepositoryPort;
import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.domain.model.account.Username;

import java.util.Optional;

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

