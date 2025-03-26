# System Patterns: Carbon Backend

## System Architecture
The Carbon Backend follows a layered architecture with clear separation of concerns, implementing principles from Domain-Driven Design (DDD). The system is structured into the following layers:

1. **Domain Layer**: Contains the core business entities, repositories interfaces, and service interfaces
2. **Application Layer**: Implements business logic, service implementations, and orchestrates domain objects
3. **Infrastructure Layer**: Provides technical capabilities like persistence, security, and web interfaces
4. **Support Layer**: Contains supporting components that may be used across the system

## Key Technical Decisions

### 1. Domain-Driven Design
- Clear separation between domain, application, and infrastructure concerns
- Rich domain model with entities representing business concepts
- Repository interfaces defined in the domain layer
- Service interfaces defined in the domain layer

### 2. Hexagonal Architecture Influence
- Core domain logic is isolated from external concerns
- Ports (interfaces) and adapters (implementations) pattern is visible
- Dependencies point inward toward the domain

### 3. Spring Boot Framework
- Leveraging Spring Boot for dependency injection, web capabilities, and data access
- Using Spring Security for authentication and authorization

### 4. Repository Pattern
- Repository interfaces define data access contracts
- JPA implementations provide the actual data access logic

### 5. Service Layer
- Services implement business logic and orchestrate operations
- Clear separation between service interfaces and implementations

### 6. Rule Pattern
- Dedicated rule classes for business rules, particularly for proposal status transitions
- Factory pattern for rule creation and management

## Design Patterns in Use

### 1. Repository Pattern
- Abstracts data access logic
- Provides collection-like interface to domain objects
- Examples: `ProposalRepository`, `ProposalDetailRepository`

### 2. Factory Pattern
- Creates complex objects, particularly rules
- Example: `ProposalStatusRuleFactory`

### 3. Strategy Pattern
- Different implementations of business rules
- Example: Various rule implementations for proposal status transitions

### 4. Mapper Pattern
- Converts between domain entities and DTOs
- Examples: `ProposalMapper`, `ProposalDetailMapper`

### 5. DTO Pattern
- Data Transfer Objects for API requests and responses
- Separates domain model from API representation
- Examples: Various request and response DTOs

### 6. Dependency Injection
- Spring-managed components with constructor injection
- Promotes loose coupling and testability

## Component Relationships

### Core Domain Components
- **Proposal**: Central entity representing a sales proposal
- **ProposalDetail**: Contains detailed information about a proposal
- **ProposalDetailVehicle**: Vehicle information related to a proposal
- **ProposalDocument**: Documents associated with a proposal
- **ProposalCommission**: Commission information for a proposal
- **ProposalFup**: Follow-up information for proposals

### Status Management
- **ProposalStatus**: Enum defining possible proposal statuses
- **ProposalStatusRule**: Interface for status transition rules
- **Rule Implementations**: Specific rules for status transitions (e.g., `FinalizadoComVendaRule`)
- **ProposalStatusService**: Service managing status transitions

### Service Layer
- **ProposalService**: Core service for proposal management
- **CompleteProposalService**: Service for handling complete proposals
- **Various Detail Services**: Services for managing specific aspects of proposals

### Controllers
- REST controllers exposing API endpoints for various proposal operations
- Analysis controllers for reporting and analytics

## Data Flow
1. HTTP requests are received by controllers
2. Controllers convert request DTOs to domain objects using mappers
3. Services process the business logic using domain objects
4. Repositories persist changes to the database
5. Results are mapped back to response DTOs and returned

## Cross-Cutting Concerns
- **Security**: JWT-based authentication and authorization
- **Exception Handling**: Custom exceptions for business and technical errors
- **Configuration**: Spring configuration for various components
