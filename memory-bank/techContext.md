# Technical Context: Carbon Backend

## Technologies Used

### Core Framework
- **Java**: The primary programming language
- **Spring Boot**: Application framework providing dependency injection, web capabilities, and more
- **Spring Data JPA**: Data access framework for JPA repositories
- **Spring Security**: Authentication and authorization framework

### Database
- **SQL Database**: Likely using a relational database (specific type not explicitly visible)
- **JPA/Hibernate**: ORM for database interaction
- **SQL Scripts**: Custom SQL for complex queries (visible in resources/db directory)

### API
- **REST**: RESTful API design for all endpoints
- **JSON**: Data format for API requests and responses
- **Spring MVC**: Web framework for handling HTTP requests

### Testing
- **JUnit**: Testing framework for unit tests
- **Spring Test**: Testing support for Spring components

### Documentation
- **Markdown**: Documentation format (visible in resources/documentation)
- **Postman Collections**: API documentation and testing (visible in resources/static)

### Build Tools
- **Maven**: Dependency management and build tool (pom.xml present)

## Development Setup

### Prerequisites
- Java Development Kit (JDK)
- Maven
- SQL Database (likely MySQL, PostgreSQL, or similar)
- IDE (likely IntelliJ IDEA or Eclipse)

### Project Structure
- Standard Maven project structure
- Spring Boot application layout
- Domain-driven design package organization

### Configuration
- **application.yml**: Main configuration file for Spring Boot
- **Database Scripts**: SQL scripts for database setup and queries

## Technical Constraints

### Performance
- Must handle proposal data efficiently
- Database queries should be optimized for performance
- API responses should be timely

### Security
- JWT-based authentication
- Role-based access control
- Secure handling of proposal data

### Scalability
- Design should support growth in proposal volume
- Services should be stateless where possible

### Maintainability
- Clear separation of concerns
- Well-defined interfaces
- Comprehensive test coverage

## Dependencies

### Direct Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Database driver (likely MySQL, PostgreSQL, or similar)
- JWT library for authentication
- Mapping libraries (possibly MapStruct)

### Development Dependencies
- Spring Boot Starter Test
- JUnit
- Mockito (likely)
- Maven plugins

## Integration Points

### External Systems
- Potentially integrates with other systems (not explicitly visible)
- May have external API dependencies

### Internal Components
- Clear separation between domain components
- Well-defined interfaces between layers

## Deployment Considerations

### Environment Configuration
- Different configurations for development, testing, and production
- Environment-specific properties in application.yml

### Database Migration
- SQL scripts for database setup and migration

### Monitoring
- Logging configuration
- Potential integration with monitoring tools

## Development Workflow

### Code Organization
- Domain-driven package structure
- Clear separation of concerns
- Interface-based design

### Testing Strategy
- Unit tests for business logic
- Integration tests for repositories and services
- API tests using Postman collections

### Documentation
- Markdown files for system documentation
- API documentation through Postman collections
- Code comments for complex logic
