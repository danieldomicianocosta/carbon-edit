package com.carbon.refactor.infrastructure.security;

/**
 * Thread-local storage for the current user's information.
 * This class provides static methods to store and retrieve user information
 * for the current thread, making it available throughout the request lifecycle.
 */
public class UserContext {
    private static final ThreadLocal<UserInfo> currentUser = new ThreadLocal<>();

    /**
     * Set the current user information for this thread.
     * 
     * @param userInfo The user information to store
     */
    public static void setCurrentUser(UserInfo userInfo) {
        currentUser.set(userInfo);
    }

    /**
     * Get the current user information for this thread.
     * 
     * @return The current user information, or null if not set
     */
    public static UserInfo getCurrentUser() {
        return currentUser.get();
    }

    /**
     * Clear the current user information for this thread.
     * This should be called at the end of request processing to prevent memory leaks.
     */
    public static void clear() {
        currentUser.remove();
    }

    /**
     * Class representing user information extracted from the JWT token.
     */
    public static class UserInfo {
        private final String username;
        private final String role;

        /**
         * Create a new UserInfo instance.
         * 
         * @param username The username (from the 'sub' claim)
         * @param role The user's role (from the 'role' claim)
         */
        public UserInfo(String username, String role) {
            this.username = username;
            this.role = role;
        }

        /**
         * Get the username.
         * 
         * @return The username
         */
        public String getUsername() {
            return username;
        }

        /**
         * Get the user's role.
         * 
         * @return The role
         */
        public String getRole() {
            return role;
        }
        
        @Override
        public String toString() {
            return "UserInfo{username='" + username + "', role='" + role + "'}";
        }
    }
}
