package org.application.crm_DDD.features.user_context.infrastructure.config.use_case;

import org.application.crm_DDD.features.user_context.application.use_case.register_user.RegisterUserPasswordEncoderPort;
import org.application.crm_DDD.features.user_context.application.use_case.register_user.RegisterUserRepositoryPort;
import org.application.crm_DDD.features.user_context.application.use_case.register_user.RegisterUserService;
import org.application.crm_DDD.features.user_context.application.use_case.register_user.RegisterUserUseCase;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.register_user.RegisterUserPasswordEncoderAdapter;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.register_user.RegisterUserRepository;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.register_user.RegisterUserRepositoryAdapter;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.register_user.RegisterUserUseCaseDecorator;
import org.application.crm_DDD.features.user_context.infrastructure.security.provider.PasswordEncoderProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegisterUserConfiguration {


    @Bean
    public RegisterUserRepositoryPort registerUserRepositoryPort(final RegisterUserRepository registerUserRepository) {
        return new RegisterUserRepositoryAdapter(registerUserRepository);
    }

    @Bean
    public RegisterUserPasswordEncoderPort registerUserPasswordEncoderPort(
            final PasswordEncoderProvider passwordEncoderProvider
    ) {
        return new RegisterUserPasswordEncoderAdapter(passwordEncoderProvider);
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(
            final RegisterUserRepositoryPort registerUserRepositoryPort,
            final RegisterUserPasswordEncoderPort registerUserPasswordEncoderPort
    ) {
        var registerUserService = new RegisterUserService(registerUserRepositoryPort, registerUserPasswordEncoderPort);

        return new RegisterUserUseCaseDecorator(registerUserService);
    }

}

