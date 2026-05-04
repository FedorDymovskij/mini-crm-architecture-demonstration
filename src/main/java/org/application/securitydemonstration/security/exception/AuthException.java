package org.application.securitydemonstration.security.exception;

public class AuthException {

    public static class AuthenticationFailed extends RuntimeException {
        public AuthenticationFailed() {
            super("Authentication failed");
        }
    }

}
