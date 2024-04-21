package com.khun.movievault.advice;

import com.khun.movievault.exception.UserInvalidCredentialException;
import com.khun.movievault.exception.UserAlreadyExistException;
import com.khun.movievault.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MovieVaultExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistException.class)
    public Map<String, String> handleUserDuplicateErrors(UserAlreadyExistException userAlreadyExistException){
        var errorMap = new HashMap<String, String>();
        errorMap.put("Error Message", userAlreadyExistException.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Map<String, String> handleUserNotFoundError(NotFoundException notFoundException){
        var errorMap = new HashMap<String, String>();
        errorMap.put("Error Message", notFoundException.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserInvalidCredentialException.class)
    public Map<String, String> handleInvalidUserError(UserInvalidCredentialException invalidUserCredentialException){
        var errorMap = new HashMap<String, String>();
        errorMap.put("Error Message", invalidUserCredentialException.getMessage());
        return errorMap;
    }
}
