package org.application.securitydemonstration.security.exception;

import java.util.UUID;

public class TokenException {
    public static class RefreshTokenNotFound extends RuntimeException {
        public RefreshTokenNotFound(final UUID refreshTokenUUID) {
            super("Refresh token with uuid: " + refreshTokenUUID + " not found");
        }

        public RefreshTokenNotFound(final Long tokenId) {
            super("Refresh token with id: " + tokenId + " not found");
        }

        public RefreshTokenNotFound() {
            super("Refresh token not found");
        }
    }

    public static class CookieRefreshTokenNotFound extends RuntimeException {
        public CookieRefreshTokenNotFound() {
            super("Refresh token not found in cookie");
        }
    }

    public static class RefreshTokenExpired extends RuntimeException {
        public RefreshTokenExpired(final UUID refreshTokenUUID) {
            super("Refresh token with uuid: " + refreshTokenUUID + " is expired");
        }

        public RefreshTokenExpired(final Long tokenId) {
            super("Refresh token with id: " + tokenId + " is expired");
        }

        public RefreshTokenExpired() {
            super("Refresh token is expired");
        }
    }

    public static class RefreshTokenRevoked extends RuntimeException {
        public RefreshTokenRevoked() {
            super("Refresh token revoked");
        }

        public RefreshTokenRevoked(final UUID refreshTokenUUID) {
            super("Refresh token with uuid: " + refreshTokenUUID + " is revoked");
        }
    }

    public static class RefreshTokenRefreshed extends RuntimeException {
        public RefreshTokenRefreshed() {
            super("Refresh token refreshed");
        }

        public RefreshTokenRefreshed(final UUID refreshTokenUUID) {
            super("Refresh token with uuid: " + refreshTokenUUID + " is refreshed");
        }
    }


}
