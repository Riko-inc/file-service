package org.example.fileservice.exceptions;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers , @NonNull HttpStatusCode statusCode, @NonNull WebRequest request) {
        List<Map<String, String>> errors = new ArrayList<>();
        for (FieldError error: ex.getBindingResult().getFieldErrors()) {
            errors.add(Map.of(
                            "type", error.getField(),
                            "message", Objects.requireNonNull(error.getDefaultMessage()),
                            "value", String.valueOf(error.getRejectedValue())));
        }
        for (ObjectError error: ex.getBindingResult().getGlobalErrors()) {
            errors.add(Map.of(
                            "type", error.getObjectName(),
                            "message", Objects.requireNonNull(error.getDefaultMessage())));
        }

        CustomError customError = new CustomError(statusCode.value(), ex.getClass().getSimpleName(), errors);

        return handleExceptionInternal(ex, customError, headers, HttpStatusCode.valueOf(customError.getStatus()), request);
    }

    @ExceptionHandler({EntityNotFoundException.class, UsernameNotFoundException.class})
    public ResponseEntity<Object> handleUsernameNotFoundException(EntityNotFoundException ex) {
        List<Map<String, String>> errors = new ArrayList<>();
            errors.add(Map.of("message", ex.getMessage()));
        return new ResponseEntity<>(new CustomError(HttpStatus.NOT_FOUND.value(), ex.getClass().getSimpleName(), errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {
        List<Map<String, String>> errors = new ArrayList<>();
        errors.add(Map.of("message", ex.getMessage()));
        return new ResponseEntity<>(new CustomError(HttpStatus.UNAUTHORIZED.value(), ex.getClass().getSimpleName(), errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(Exception ex) {
        List<Map<String, String>> errors = new ArrayList<>();
        errors.add(Map.of("message", ex.getMessage()));
        return new ResponseEntity<>(new CustomError(HttpStatus.NOT_FOUND.value(), ex.getClass().getSimpleName(), errors), HttpStatus.NOT_FOUND);
    }
}
