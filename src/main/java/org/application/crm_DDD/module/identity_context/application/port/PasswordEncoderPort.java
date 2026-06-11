package org.application.crm_DDD.module.identity_context.application.port;

import org.application.crm_DDD.module.identity_context.domain.model.account.Password;

public interface PasswordEncoderPort {
    String encode(final String rawPassword);

    boolean matches(
            final Password rawPassword,
            final Password hashPassword
    );
}
