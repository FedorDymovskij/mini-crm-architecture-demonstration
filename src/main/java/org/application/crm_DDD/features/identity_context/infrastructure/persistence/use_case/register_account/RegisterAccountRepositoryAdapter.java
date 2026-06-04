package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.register_account;

import org.application.crm_DDD.features.identity_context.application.use_case.register_account.RegisterAccountRepositoryPort;
import org.application.crm_DDD.features.identity_context.domain.model.account.Account;
import org.application.crm_DDD.features.identity_context.domain.model.account.Username;
import org.application.crm_DDD.features.identity_context.infrastructure.persistence.entity.IdentityContextAccountEntity;
import org.springframework.stereotype.Component;

@Component
public class RegisterAccountRepositoryAdapter implements RegisterAccountRepositoryPort {
    private final RegisterAccountRepository userRepository;

    @Override
    public boolean existsByUsername(final Username username) {
        return this.userRepository.existsByUsername(username.value());
    }

    @Override
    public void save(
            final Account account
    ) {
        Account.Snapshot snapshot = account.toSnapshot();

        var registerUserEntity = new IdentityContextAccountEntity(
                snapshot.id(),
                snapshot.username(),
                snapshot.password(),
                snapshot.role(),
                snapshot.status()
        );

        this.userRepository.save(registerUserEntity);
    }

    public RegisterAccountRepositoryAdapter(RegisterAccountRepository userRepository) {
        this.userRepository = userRepository;
    }
}
