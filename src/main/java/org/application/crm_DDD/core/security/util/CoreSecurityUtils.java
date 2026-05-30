package org.application.crm_DDD.core.security.util;

import org.application.crm_DDD.core.security.CorePrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class CoreSecurityUtils {

    public Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }

    public Optional<UUID> getAuthenticatedAccountId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }

        if (authentication.getDetails() instanceof CorePrincipal principal) {
            return Optional.ofNullable(principal.getAccountId());
        }
        return Optional.empty();
    }


}
