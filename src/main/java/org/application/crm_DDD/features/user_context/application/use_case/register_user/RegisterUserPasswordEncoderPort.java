package org.application.crm_DDD.features.user_context.application.use_case.register_user;

public interface RegisterUserPasswordEncoderPort {
    String encode(final String rawPassword);
}
