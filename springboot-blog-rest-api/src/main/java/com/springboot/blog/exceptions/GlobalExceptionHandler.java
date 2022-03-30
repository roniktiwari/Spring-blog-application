package com.springboot.blog.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // This class will handle specific exception as well as global exception

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error>  handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        Error error = new Error(new Date(),exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<Error>  handleBlogApiException(BlogApiException exception, WebRequest request) {
        Error error = new Error(new Date(),exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid (
                                                    MethodArgumentNotValidException ex,
                                                    HttpHeaders headers,
                                                    HttpStatus status,
                                                    WebRequest request) {

        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach( error -> {


            String fieldName = ((FieldError)error).getField();
            String fieldErrorMessage = ((FieldError)error).getDefaultMessage() ;
            errors.put(fieldName,fieldErrorMessage);
        } );
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGlobalException(Exception exception , WebRequest webRequest) {

        Error error = new Error(new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
