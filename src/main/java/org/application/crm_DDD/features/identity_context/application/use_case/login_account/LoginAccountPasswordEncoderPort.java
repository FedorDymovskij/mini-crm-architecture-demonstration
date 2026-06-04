package org.application.crm_DDD.features.identity_context.application.use_case.login_account;

import org.application.crm_DDD.features.identity_context.domain.model.account.Password;

public interface LoginAccountPasswordEncoderPort {
    boolean matches(
            final Password rawPassword,
            final Password hashPassword
    );
}
