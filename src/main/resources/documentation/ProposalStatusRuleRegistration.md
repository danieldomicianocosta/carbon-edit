# Proposal Status Rule Registration Guide

This document explains the different ways to register rules for proposal status transitions in the system.

## 1. Automatic Registration with Spring Component Scanning

The simplest way to register a rule is to create a class that implements the `ProposalStatusRule` interface and annotate it with `@Component`. Spring will automatically detect and register these rules during application startup.

```java
@Component
public class MyCustomRule implements ProposalStatusRule {
    
    @Override
    public boolean executeRule(CompleteProposalResponseDTO proposal, Integer currentStatus, Integer newStatus) {
        // Rule logic here
        return true;
    }
    
    @Override
    public Integer getSourceStatus() {
        return ProposalStatus.SOME_STATUS.getId();
    }
    
    @Override
    public Integer getTargetStatus() {
        return ProposalStatus.ANOTHER_STATUS.getId();
    }
}
```

The `ProposalStatusRuleFactory` class has a `@PostConstruct` method that automatically finds and registers all beans that implement the `ProposalStatusRule` interface:

```java
@PostConstruct
public void init() {
    // Get all beans that implement ProposalStatusRule
    String[] beanNames = applicationContext.getBeanNamesForType(ProposalStatusRule.class);
    
    // Register each rule with the service
    for (String beanName : beanNames) {
        ProposalStatusRule rule = applicationContext.getBean(beanName, ProposalStatusRule.class);
        proposalStatusService.registerRule(rule);
    }
}
```

## 2. Manual Registration in a Configuration Class

For more control over rule registration, you can manually register rules in a configuration class. This approach is useful when you want to register rules conditionally based on configuration properties.

```java
@Configuration
public class ManualRuleRegistrationConfig {
    
    @Bean
    public ProposalStatusService registerConcreteRules(
            ProposalStatusService proposalStatusService,
            MyCustomRule myCustomRule,
            AnotherRule anotherRule) {
        
        // Register each concrete rule with the service
        proposalStatusService.registerRule(myCustomRule);
        proposalStatusService.registerRule(anotherRule);
        
        return proposalStatusService;
    }
}
```

## 3. Programmatic Registration with the Factory

The `ProposalStatusRuleFactory` provides a convenient way to create and register rules programmatically without creating separate classes. This is useful for simple rules or when you want to create rules dynamically.

```java
@Configuration
public class FactoryRuleRegistrationConfig {
    
    @Bean
    public ProposalStatusRuleFactory configureFactoryRules(ProposalStatusRuleFactory ruleFactory) {
        // Create and register a rule using a lambda expression
        ruleFactory.createRule(
                ProposalStatus.SOURCE_STATUS.getId(),
                ProposalStatus.TARGET_STATUS.getId(),
                (proposal, currentStatus, newStatus) -> {
                    // Rule logic here
                    return true;
                });
        
        return ruleFactory;
    }
}
```

## 4. Direct Registration in Code

You can also register rules directly in your code by injecting the `ProposalStatusService` and calling the `registerRule` method:

```java
@Service
public class MyService {
    
    private final ProposalStatusService proposalStatusService;
    
    public MyService(ProposalStatusService proposalStatusService) {
        this.proposalStatusService = proposalStatusService;
        
        // Create and register a rule
        ProposalStatusRule rule = new ProposalStatusRule() {
            @Override
            public boolean executeRule(CompleteProposalResponseDTO proposal, Integer currentStatus, Integer newStatus) {
                // Rule logic here
                return true;
            }
            
            @Override
            public Integer getSourceStatus() {
                return ProposalStatus.SOURCE_STATUS.getId();
            }
            
            @Override
            public Integer getTargetStatus() {
                return ProposalStatus.TARGET_STATUS.getId();
            }
        };
        
        proposalStatusService.registerRule(rule);
    }
}
```

## Best Practices

1. **Choose the Right Approach**: Use the approach that best fits your needs:
   - For complex rules with dependencies, create a concrete class and use automatic registration.
   - For simple rules, use the factory with lambda expressions.
   - For conditional registration, use a configuration class.

2. **Rule Naming**: Give your rules descriptive names that indicate what they validate and for which transition.

3. **Rule Organization**: Group related rules together in the same package or configuration class.

4. **Rule Testing**: Write unit tests for your rules to ensure they work correctly.

5. **Rule Documentation**: Document your rules with clear comments explaining what they validate and why.

6. **Custom Error Messages**: Provide clear and specific error messages for your rules to help users understand why a transition was rejected.
   - For concrete rule classes, override the `getErrorMessage()` method.
   - For factory-created rules, use the overloaded `createRule` method that accepts an error message.

## Example: Complete Rule Registration Flow

Here's a complete example of how to register rules in a Spring Boot application:

1. Create a concrete rule class:

```java
@Component
public class CustomerEmailRule implements ProposalStatusRule {
    
    @Override
    public boolean executeRule(CompleteProposalResponseDTO proposal, Integer currentStatus, Integer newStatus) {
        String email = proposal.getProposal().getCustomerEmail();
        return email != null && email.contains("@");
    }
    
    @Override
    public Integer getSourceStatus() {
        return ProposalStatus.PROPOSTA_ENVIADA.getId();
    }
    
    @Override
    public Integer getTargetStatus() {
        return ProposalStatus.VALIDACAO_BACKOFFICE.getId();
    }
}
```

Example of a rule that checks user roles with a custom error message:

```java
@Component
public class FinalizadoComVendaToCanceladoRule implements ProposalStatusRule {
    
    private static final String COM_CEO_ROLE = "COM_CEO";
    private static final String ERROR_MESSAGE = "Apenas usuários com o papel COM_CEO podem cancelar propostas finalizadas com venda.";
    
    @Override
    public boolean executeRule(CompleteProposalResponseDTO proposal, Integer currentStatus, Integer newStatus) {
        // Check if this rule applies to the current transition
        if (!getSourceStatus().equals(currentStatus) || !getTargetStatus().equals(newStatus)) {
            // If this rule doesn't apply to the current transition, return true
            return true;
        }
        
        // Get the current user from the UserContext
        UserContext.UserInfo currentUser = UserContext.getCurrentUser();
        
        // Rule logic: Only users with COM_CEO role can transition from FINALIZADO_COM_VENDA to CANCELADO
        if (currentUser == null || currentUser.getRole() == null) {
            return false;
        }
        
        return COM_CEO_ROLE.equals(currentUser.getRole());
    }
    
    @Override
    public Integer getSourceStatus() {
        return ProposalStatus.FINALIZADO_COM_VENDA.getId();
    }
    
    @Override
    public Integer getTargetStatus() {
        return ProposalStatus.CANCELADO.getId();
    }
    
    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
```

2. Create a configuration class for programmatic rules:

```java
@Configuration
public class RuleConfig {
    
    @Bean
    public ProposalStatusRuleFactory configureRules(ProposalStatusRuleFactory ruleFactory) {
        // Rule without custom error message
        ruleFactory.createRule(
                ProposalStatus.EM_EDICAO.getId(),
                ProposalStatus.EM_APROVACAO_COMERCIAL.getId(),
                (proposal, currentStatus, newStatus) -> {
                    return proposal.getProposal().getCustomerName() != null;
                });
        
        // Rule with custom error message
        ruleFactory.createRule(
                ProposalStatus.PROPOSTA_ENVIADA.getId(),
                ProposalStatus.FINALIZADO_SEM_VENDA.getId(),
                (proposal, currentStatus, newStatus) -> {
                    return proposal.getProposal().getFinishedWithoutSaleClaId() != null;
                },
                "É necessário informar o motivo de finalização sem venda.");
        
        return ruleFactory;
    }
}
```

3. The rules will be automatically registered and executed during status transitions.
