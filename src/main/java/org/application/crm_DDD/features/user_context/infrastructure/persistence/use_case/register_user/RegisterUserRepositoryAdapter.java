package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.register_user;

import org.application.crm_DDD.features.user_context.application.use_case.register_user.RegisterUserRepositoryPort;
import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.domain.model.account.Username;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.entity.UserContextAccountEntity;

public class RegisterUserRepositoryAdapter implements RegisterUserRepositoryPort {
    private final RegisterUserRepository userRepository;

    @Override
    public boolean existsByUsername(final Username username) {
        return this.userRepository.existsByUsername(username.value());
    }

    @Override
    public void save(
            final Account account
    ) {
        Account.Snapshot snapshot = account.toSnapshot();

        var registerUserEntity = new UserContextAccountEntity(
                snapshot.id(),
                snapshot.username(),
                snapshot.password(),
                snapshot.role(),
                snapshot.status()
        );

        this.userRepository.save(registerUserEntity);
    }

    public RegisterUserRepositoryAdapter(RegisterUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
