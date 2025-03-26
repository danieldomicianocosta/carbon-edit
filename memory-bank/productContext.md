# Product Context: Carbon Backend

## Why This Project Exists
The Carbon Backend project exists to provide a comprehensive system for managing business proposals in what appears to be a vehicle sales context. It serves as the core backend infrastructure that enables sales teams to create, track, and finalize proposals while ensuring compliance with business rules and workflows.

## Problems It Solves
1. **Proposal Management Complexity**: Simplifies the complex process of creating and managing sales proposals with multiple components (vehicles, documents, commissions)
2. **Workflow Enforcement**: Ensures proposals follow the correct status transitions according to business rules
3. **Data Fragmentation**: Centralizes proposal data that might otherwise be scattered across different systems
4. **Reporting Challenges**: Provides analysis capabilities to understand proposal performance and status distribution
5. **Compliance Concerns**: Enforces proper validation and documentation requirements
6. **Access Control**: Manages who can view and modify proposal information

## How It Should Work
1. **Proposal Creation**: Users can create new proposals with essential details
2. **Status Management**: The system enforces valid status transitions based on business rules (e.g., from "ValidacaoBackoffice" to "FinalizadoComVenda" or "FinalizadoSemVenda")
3. **Detail Management**: Users can add and update proposal details, including vehicle information, documents, and commission data
4. **Validation**: The system validates proposals at different stages according to business requirements
5. **Analysis**: Provides endpoints for analyzing proposal data, such as counts by status
6. **Security**: Implements JWT-based authentication and authorization to control access

## User Experience Goals
1. **Reliability**: The system should consistently enforce business rules and maintain data integrity
2. **Responsiveness**: API endpoints should respond quickly to support an efficient frontend experience
3. **Completeness**: All necessary proposal data should be accessible through well-defined APIs
4. **Consistency**: Business rules should be applied consistently across all operations
5. **Traceability**: Changes to proposals should be tracked and attributable
6. **Flexibility**: The system should accommodate various proposal scenarios while maintaining rule enforcement

## Target Users
1. **Sales Representatives**: Creating and managing proposals for customers
2. **Backoffice Staff**: Validating and processing proposals
3. **Managers**: Analyzing proposal performance and status
4. **System Administrators**: Managing access and system configuration
5. **Integration Systems**: Other systems that may interact with the proposal data

## Business Value
1. **Increased Sales Efficiency**: Streamlined proposal process leads to more efficient sales operations
2. **Improved Compliance**: Consistent enforcement of business rules ensures regulatory compliance
3. **Better Decision Making**: Analysis capabilities provide insights for business decisions
4. **Reduced Errors**: Validation rules prevent common mistakes in the proposal process
5. **Enhanced Customer Experience**: A smooth proposal process improves the overall customer journey
