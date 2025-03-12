package com.carbon.refactor.domain.exception;

public class ForeignKeyConstraintViolationException extends BusinessException {
    
    private static final long serialVersionUID = 1L;
    
    public ForeignKeyConstraintViolationException(String message) {
        super(message);
    }
    
    public ForeignKeyConstraintViolationException(String entityName, String foreignKeyName, Object value) {
        super(String.format("Foreign key constraint violation: %s with %s %s does not exist", 
                entityName, foreignKeyName, value));
    }
}
