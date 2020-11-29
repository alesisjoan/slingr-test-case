// Copyright(c) 2016 by TimeTrade Systems.  All Rights Reserved.
/**
 * @author kwang
 */
package com.github.alesisjoan.slingr.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Defines global exception handling behavior.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Collects all of the invalid parameter validation constraints and bundles them into a single errors object.
     *
     * @param exception MethodArgumentNotValidException Errors related to invalid object parameters.
     * @return Errors object that holds all of the validation errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Errors handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        log.debug("MethodArgumentNotValidException handler triggered.", exception);
        BindingResult bindingResult = exception.getBindingResult();

        List<Errors.FieldError> globalErrorList = handleMethodArgumentNotValidGlobals(bindingResult);
        List<Errors.FieldError> fieldErrorList = handleMethodArgumentNotValidFields(bindingResult);
        globalErrorList.addAll(fieldErrorList);

        return new Errors(HttpStatus.BAD_REQUEST.value(), globalErrorList);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Errors handleConstraintViolationException(ConstraintViolationException exception) {
        log.debug("ConstraintViolationException handler triggered.", exception);
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();

        List<Errors.FieldError> globalErrorList = constraintViolations.stream().map(cv -> new Errors.FieldError(
                cv.getPropertyPath().toString(),
                (String) cv.getInvalidValue(),
                cv.getMessage())).collect(Collectors.toList());
        return new Errors(HttpStatus.BAD_REQUEST.value(), globalErrorList);
    }
    
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ResponseBody
    public Errors handleNoHandlerFoundException(NoHandlerFoundException exception) {
        log.debug("NoHandlerFoundException handler triggered.", exception);
        List<Errors.FieldError> fieldErrorList = new ArrayList<>();
        fieldErrorList.add(new Errors.FieldError(null, "NotFound.Resource", exception.getMessage()));
        return new Errors(HttpStatus.NOT_FOUND.value(), fieldErrorList);
    }

//    @ExceptionHandler(ConfigurationAPINotFoundException.class)
//    @ResponseStatus(value=HttpStatus.NOT_FOUND)
//    @ResponseBody
//    public Errors handleConfigurationAPINotFoundException(ConfigurationAPINotFoundException exception) {
//        log.debug("ConfigurationAPINotFoundException handler triggered.", exception);
//        List<Errors.FieldError> fieldErrorList = new ArrayList<>();
//        fieldErrorList.add(new Errors.FieldError(null, "NotFound.Resource", exception.getMessage()));
//        return new Errors(HttpStatus.NOT_FOUND.value(), fieldErrorList);
//    }
   
    @ExceptionHandler(BadRequest.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Errors handleConfigurationBadRequestException(BadRequest exception) {
        log.debug("BadRequest handler triggered.", exception);
        List<Errors.FieldError> fieldErrorList = new ArrayList<>();
        fieldErrorList.add(new Errors.FieldError(null, "Bad.Request", exception.getMessage()));
        return new Errors(HttpStatus.BAD_REQUEST.value(), fieldErrorList);
    }
    
    @ExceptionHandler(MathException.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Errors handleMathException(MathException exception) {
        log.debug("MathException handler triggered.", exception);
        List<Errors.FieldError> fieldErrorList = new ArrayList<>();
        fieldErrorList.add(new Errors.FieldError(null, "Internal.error", exception.getMessage()));
        return new Errors(HttpStatus.INTERNAL_SERVER_ERROR.value(), fieldErrorList);
    }
    
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Errors handleRuntimeException(Exception ex) {
        log.debug("Exception handler triggered by runtime exception.", ex);
        String errorMessage = "Server internal error.";
        return new Errors(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                          Collections.singletonList(new Errors.FieldError(null, "Internal.error", errorMessage)));
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Errors handleAll(Exception ex) {
        log.debug("Exception handler triggered.", ex);
        String errorMessage = "There was a problem reading the request. Make sure you have provided valid values for all fields.";
        return new Errors(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                          Collections.singletonList(new Errors.FieldError(null, "Invalid.json.request", errorMessage)));
    }

    private List<Errors.FieldError> handleMethodArgumentNotValidFields(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream().map(fieldFieldError -> new Errors.FieldError(
                fieldFieldError.getField(),
                fieldFieldError.getCodes()[0],
                fieldFieldError.getDefaultMessage())).collect(Collectors.toList());
    }

    private List<Errors.FieldError> handleMethodArgumentNotValidGlobals(BindingResult bindingResult) {
        List<Errors.FieldError> fieldErrorList = new ArrayList<>();

        for (ObjectError globalError : bindingResult.getGlobalErrors()) {
            fieldErrorList.add(new Errors.FieldError(null, globalError.getCodes()[0], globalError.getDefaultMessage()));
        }

        return fieldErrorList;
    }
}
