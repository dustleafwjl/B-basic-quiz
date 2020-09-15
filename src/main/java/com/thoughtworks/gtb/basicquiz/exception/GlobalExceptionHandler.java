package com.thoughtworks.gtb.basicquiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResult handler(MethodArgumentNotValidException exception) {
        String defaultMessage = Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage();
        return ErrorResult.builder().status(400).timestamp(new Date().toString()).message(defaultMessage).error("Bad Request").build();
    }

    @ExceptionHandler(UserIsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResult handler(UserIsNotFoundException exception) {
        String defaultMessage = exception.getMessage();
        return ErrorResult.builder().status(404).timestamp(new Date().toString()).message(defaultMessage).error("Not Found").build();
    }

    @ExceptionHandler(UserHasNotEducationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResult handler(UserHasNotEducationException exception) {
        String defaultMessage = exception.getMessage();
        return ErrorResult.builder().status(404).timestamp(new Date().toString()).message(defaultMessage).error("Not Found").build();
    }
}

