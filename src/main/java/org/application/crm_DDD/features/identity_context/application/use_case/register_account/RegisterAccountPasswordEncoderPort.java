package org.application.crm_DDD.features.identity_context.application.use_case.register_account;

public interface RegisterAccountPasswordEncoderPort {
    String encode(final String rawPassword);
}
