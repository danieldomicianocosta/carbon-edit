package com.carbon.refactor.infrastructure.security;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility class for access control operations.
 * This class provides methods to check user access based on the information
 * extracted from JWT tokens and stored in the UserContext.
 */
@Component
@Slf4j
public class AccessControlUtil {

    /**
     * Check if the current user has access to a specific resource.
     * This is a placeholder implementation that can be extended to use the AccessList entity
     * and related repositories to perform actual access control checks.
     * 
     * @param resourceId The ID of the resource to check access for
     * @return true if the user has access, false otherwise
     */
    public boolean hasAccess(Integer resourceId) {
        UserContext.UserInfo userInfo = UserContext.getCurrentUser();
        
        if (userInfo == null) {
            log.warn("No user information available in context");
            return false;
        }
        
        log.debug("Checking access for user: {}, role: {}, resource: {}", 
                userInfo.getUsername(), userInfo.getRole(), resourceId);
        
        // This is where you would implement your actual access control logic
        // For example, you could check if the user's role has access to the resource
        // or if the user is in a specific access list
        
        // Example implementation:
        // return accessListRepository.hasAccess(userInfo.getUsername(), userInfo.getRole(), resourceId);
        
        // For now, we'll just return true for demonstration purposes
        return true;
    }
    
    /**
     * Check if the current user has a specific role.
     * 
     * @param role The role to check for
     * @return true if the user has the role, false otherwise
     */
    public boolean hasRole(String role) {
        UserContext.UserInfo userInfo = UserContext.getCurrentUser();
        
        if (userInfo == null) {
            log.warn("No user information available in context");
            return false;
        }
        
        return role.equals(userInfo.getRole());
    }
    
    /**
     * Get the current username from the context.
     * 
     * @return The username, or null if not available
     */
    public String getCurrentUsername() {
        UserContext.UserInfo userInfo = UserContext.getCurrentUser();
        return userInfo != null ? userInfo.getUsername() : null;
    }
    
    /**
     * Get the current user role from the context.
     * 
     * @return The user role, or null if not available
     */
    public String getCurrentUserRole() {
        UserContext.UserInfo userInfo = UserContext.getCurrentUser();
        return userInfo != null ? userInfo.getRole() : null;
    }
}
