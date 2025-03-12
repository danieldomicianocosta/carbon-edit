package com.carbon.refactor.domain.exception;

public class EntityNotFoundException extends BusinessException {
    
    private static final long serialVersionUID = 1L;
    
    public EntityNotFoundException(String message) {
        super(message);
    }
    
    public EntityNotFoundException(String entityName, Integer id) {
        super(String.format("%s with id %d not found", entityName, id));
    }
    
    public EntityNotFoundException(String entityName, String identifier, String value) {
        super(String.format("%s with %s %s not found", entityName, identifier, value));
    }
}
