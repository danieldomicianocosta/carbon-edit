package com.carbon.refactor.support.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.carbon.refactor.infrastructure.security.AccessControlUtil;
import com.carbon.refactor.infrastructure.security.UserContext;
import com.carbon.refactor.support.domain.entity.AccessList;
import com.carbon.refactor.support.domain.repository.AccessListRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service that demonstrates how to use the UserContext with AccessList entities.
 * This class shows how to integrate the JWT token information with the AccessList functionality.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccessListServiceWithUserContext {
    
    private final AccessListRepository accessListRepository;
    private final AccessControlUtil accessControlUtil;
    
    /**
     * Get all access lists that the current user has access to.
     * This method demonstrates how to use the user information from the JWT token
     * to filter data based on access control rules.
     * 
     * @return List of access lists the user has access to
     */
    public List<AccessList> getAccessListsForCurrentUser() {
        UserContext.UserInfo userInfo = UserContext.getCurrentUser();
        
        if (userInfo == null) {
            log.warn("No user information available in context");
            return List.of(); // Return empty list if no user info is available
        }
        
        String username = userInfo.getUsername();
        String role = userInfo.getRole();
        
        log.info("Fetching access lists for user: {}, role: {}", username, role);
        
        // Example of how to use the user information to filter data
        // In a real implementation, you would have a repository method that filters by user/role
        List<AccessList> allAccessLists = accessListRepository.findAll();
        
        // Filter the access lists based on user role
        // This is just an example - in a real application, you would implement
        // more sophisticated filtering based on your access control rules
        if ("IT_ADMINISTRADOR".equals(role)) {
            // Administrators can see all access lists
            return allAccessLists;
        } else {
            // Other users can only see specific access lists
            // This is a simplified example - in a real application, you would
            // implement more sophisticated filtering
            return allAccessLists.stream()
                    .filter(accessList -> hasAccessToList(username, role, accessList))
                    .collect(Collectors.toList());
        }
    }
    
    /**
     * Check if a user has access to a specific access list.
     * This is a placeholder implementation that can be extended with actual business logic.
     * 
     * @param username The username
     * @param role The user's role
     * @param accessList The access list to check
     * @return true if the user has access, false otherwise
     */
    private boolean hasAccessToList(String username, String role, AccessList accessList) {
        // This is where you would implement your actual access control logic
        // For example, you could check if the user is associated with this access list
        // or if the user's role has access to this list
        
        // For demonstration purposes, we'll just return true for some access lists
        // In a real application, you would implement more sophisticated logic
        return accessList.getId() % 2 == 0; // Just an example - allow access to even-numbered lists
    }
    
    /**
     * Log access to an access list.
     * This method demonstrates how to record the user who accessed the access list.
     * 
     * @param accessListId The ID of the access list being accessed
     */
    public void logAccessListAccess(Integer accessListId) {
        // Get the current user from the context
        String username = accessControlUtil.getCurrentUsername();
        
        if (username == null) {
            log.warn("No user information available in context");
            return;
        }
        
        // Find the access list
        Optional<AccessList> accessListOpt = accessListRepository.findById(accessListId);
        if (accessListOpt.isEmpty()) {
            log.warn("Access list not found: {}", accessListId);
            return;
        }
        
        AccessList accessList = accessListOpt.get();
        log.info("User {} accessed access list: {}", username, accessList.getName());
        
        // In a real application, you would log this access to a database or audit log
        // For example: auditLogRepository.logAccess(username, "ACCESS_LIST", accessListId);
    }
    
    /**
     * Check if the current user can modify an access list.
     * 
     * @param accessListId The ID of the access list
     * @return true if the user can modify the access list, false otherwise
     */
    public boolean canModifyAccessList(Integer accessListId) {
        // Get the current user role from the context
        String role = accessControlUtil.getCurrentUserRole();
        
        if (role == null) {
            log.warn("No user role available in context");
            return false;
        }
        
        // Check if the user has the required role
        if ("IT_ADMINISTRADOR".equals(role)) {
            return true; // Administrators can modify any access list
        }
        
        // For other roles, you would implement more sophisticated logic
        // For example, checking if the user is the owner of the access list
        // or if the user has specific permissions
        
        return false; // Default to denying access
    }
}
