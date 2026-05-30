package org.application.crm_DDD.features.user_context.application.use_case.login_user;

import org.application.crm_DDD.features.user_context.domain.model.account.Password;

public interface LoginAccountPasswordEncoderPort {
    boolean matches(
            final Password rawPassword,
            final Password hashPassword
    );
}
