package com.springboot.blog.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

        private String  resourceName ;
        private String  fieldName ;
        private String  fieldValue ;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        // Post not found with id : 1
        super(String.format(" %s is not found with %s : %s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
