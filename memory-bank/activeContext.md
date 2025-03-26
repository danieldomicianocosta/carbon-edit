# Active Context: Carbon Backend

## Current Work Focus
The current focus appears to be on the core proposal management system, with particular emphasis on:

1. **Proposal Status Management**: Implementing and refining the rules for proposal status transitions
2. **Proposal Analysis**: Developing capabilities for analyzing proposal data
3. **Support Infrastructure**: Building out supporting components for the main proposal system

## Recent Changes
1. **Camel Bean Registry Fix (2025-03-26)**: Fixed bean registry issues in Camel routes:
   - Added explicit bean names to repository implementations:
     - Added `@Component("proposalRepository")` to `ProposalRepositoryImpl`
     - Added `@Component("proposalDetailRepository")` to `ProposalDetailRepositoryImpl`
     - Added `@Component("proposalDetailVehicleRepository")` to `ProposalDetailVehicleRepositoryImpl`
   - Created a new `CamelRepositoryConfig` class to register beans for Camel routes:
     - Registered JPA repositories as beans with explicit names
     - Registered mapper classes as beans with explicit names
     - Registered utility classes as beans with explicit names
   - This change was necessary because Camel was looking for beans with specific names in the registry, but Spring was registering them with default names based on their class names.

2. **Camel 4.x Simple Language Syntax Fix (2025-03-26)**: Fixed Camel 4.x Simple language syntax for accessing exchange properties:
   - Updated all instances of `${property.xxx}` to `${exchangeProperty.xxx}` in Camel routes
   - This change was necessary because Camel 4.x changed the syntax for accessing exchange properties in the Simple language
   - Modified files:
     - `FindProposalRoute.java`: Updated all occurrences of `${property.proposalId}` and `${property.detailId}`
     - `UpdateProposalRoute.java`: Updated all occurrences of `${property.proposalId}`

3. **Camel 4.x Upgrade (2025-03-26)**: Upgraded Apache Camel from version 3.20.1 to 4.4.0:
   - Updated Camel dependencies in pom.xml from 3.20.1 to 4.4.0
   - Replaced `camel-servlet-starter` with `camel-platform-http-starter` (Camel 4.x uses platform-http instead of servlet)
   - Removed workaround dependencies (`javax.servlet-api` and `jaxb-api`) as they're no longer needed with Camel 4.x
   - Updated `CamelServletConfig` to use the platform-http component instead of the servlet component
   - Updated `CamelConfig` to use the platform-http component in the REST configuration
   - Re-enabled all Camel-related components by restoring their annotations:
     - Re-enabled `CamelServletConfig` by adding back the `@Bean` annotation
     - Re-enabled `CamelCompleteProposalServiceImpl` by adding back the `@Service` and `@Primary` annotations
     - Re-enabled `CamelCompleteProposalController` by adding back the `@RestController` and `@RequestMapping` annotations
     - Re-enabled all Camel processors, routes, aggregators, and enrichers by adding back their `@Component` annotations
   
   These changes were necessary because the previous version of Camel (3.20.1) used Java EE (javax.*) packages, which were not compatible with Spring Boot 3.4.3's Jakarta EE (jakarta.*) packages. Camel 4.x has migrated to Jakarta EE, making it compatible with Spring Boot 3.4.3.

2. **Previous Dependency Fix (2025-03-26)**: Added missing dependencies to resolve compilation errors:
   - Added `javax.servlet-api` to fix `javax.servlet.http.HttpServlet not found` error in CamelServletConfig
   - Added `jaxb-api` to fix warnings related to `javax.xml.bind.annotation.XmlAccessType`
   
   These dependencies were needed because the project used Apache Camel 3.20.1 components that still relied on the older javax.* packages, even though the project had migrated to Jakarta EE (jakarta.*) packages with Spring Boot 3.4.3. These dependencies have now been removed as part of the Camel 4.x upgrade.

3. **Previous Camel Components Disabled (2025-03-26)**: Disabled Apache Camel components to resolve Jakarta EE vs Java EE compatibility issues:
   - Disabled `CamelServletConfig` by commenting out the `@Bean` annotation
   - Disabled `CamelCompleteProposalServiceImpl` by removing the `@Service` and `@Primary` annotations
   - Renamed and disabled `CompleteProposalController` to `CamelCompleteProposalController` to avoid bean name conflicts
   - Disabled all Camel-related components (processors, routes, aggregators, etc.) by removing their `@Component` annotations
   
   These changes were necessary because the Apache Camel 3.20.1 components used the older Java EE (javax.*) packages, which were not compatible with Spring Boot 3.4.3's Jakarta EE (jakarta.*) packages. These components have now been re-enabled as part of the Camel 4.x upgrade.

## Current State
Based on the file structure and naming:

1. **Core Proposal Management**: The system has a comprehensive set of components for managing proposals, including:
   - Basic proposal information
   - Detailed proposal data
   - Vehicle information
   - Document management
   - Commission tracking
   - Follow-up management

2. **Status Workflow**: A rule-based system for managing proposal status transitions is implemented, with specific rules for different status changes.

3. **Analysis Capabilities**: The system includes components for analyzing proposal data, particularly around status distribution.

4. **Security**: JWT-based authentication and authorization is implemented.

## Next Steps
Potential next steps based on the current state might include:

1. **Enhance Test Coverage**: While some test classes exist, expanding test coverage would improve reliability
2. **Documentation Refinement**: Continue improving system documentation
3. **API Enhancement**: Refine and expand API capabilities based on frontend needs
4. **Performance Optimization**: Review and optimize database queries and service implementations
5. **Feature Expansion**: Add new features based on business requirements

## Active Decisions and Considerations

### Architecture
- The system follows a domain-driven design approach with clear separation of concerns
- The rule pattern is used for managing proposal status transitions
- Repository pattern is used for data access

### Technical Debt
- There appear to be multiple implementations of the CompleteProposalService (with Backup and Old suffixes), suggesting refactoring may be needed
- Some SQL is stored in separate files rather than using JPA repositories, which may require maintenance

### Open Questions
- The specific database technology in use is not immediately clear
- The complete set of proposal statuses and valid transitions may need further documentation
- Integration points with other systems are not fully documented

## Current Challenges
Without more context, specific challenges are difficult to identify, but potential areas include:

1. **Rule Complexity**: Managing the various rules for proposal status transitions
2. **Data Consistency**: Ensuring consistency across the various proposal-related entities
3. **Performance**: Optimizing performance for potentially large volumes of proposal data
4. **Security**: Ensuring proper access control for sensitive proposal information

## Recent Discoveries
As this is the initial memory bank setup, there are no documented recent discoveries. Future updates will track important findings during development and maintenance.
