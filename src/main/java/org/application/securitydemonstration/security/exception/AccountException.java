package org.application.securitydemonstration.security.exception;

public class AccountException {

    public static class NotFound extends RuntimeException {
        public NotFound(final String username) {
            super("Account with name " + username + " not found");
        }

        public NotFound(final Long userId) {
            super("Account with id " + userId + " not found");
        }

        public NotFound() {
            super("Account not found");
        }
    }

    public static class AlreadyExists extends RuntimeException {
        public AlreadyExists(final String username) {
            super("Account with username: " + username + " already exists");
        }
    }

}
