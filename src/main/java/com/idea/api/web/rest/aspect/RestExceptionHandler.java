package com.idea.api.web.rest.aspect;

import com.idea.api.exception.BadRequestException;
import com.idea.api.exception.NotFoundException;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

//    @ExceptionHandler(ConstraintViolationsException.class)
//    // TODO: implement exception handling with Spring Errors object and i18n
//    public ResponseEntity<String> handleApiException(ConstraintViolationsException exception) {
//        String violationsAsString = exception.violations()
//                .stream()
//                .map(ConstraintViolation::messageKey)
//                .collect(Collectors.joining(","));
//        return new ResponseEntity<>(violationsAsString, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}