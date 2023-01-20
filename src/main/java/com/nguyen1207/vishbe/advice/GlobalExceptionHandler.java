package com.nguyen1207.vishbe.advice;

import com.nguyen1207.vishbe.dtos.response.ErrorResponseDto;
import com.nguyen1207.vishbe.exceptions.BadRequestException;
import com.nguyen1207.vishbe.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseDto<Map<String, String>> handleFailedValidation(MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> map.put(error.getField(), error.getDefaultMessage()));

        return new ErrorResponseDto<>(HttpStatus.BAD_REQUEST, map, "Validation failed");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto<Void> handleInternalError() {
        return new ErrorResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, null, "Internal Server Error");
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto<Void> handleNotFound(NotFoundException ex) {
        return new ErrorResponseDto<>(HttpStatus.NOT_FOUND, null, ex.getReason());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto<Void> handleBadRequest(BadRequestException ex) {
        return new ErrorResponseDto<>(HttpStatus.BAD_REQUEST, null, ex.getReason());
    }
}
