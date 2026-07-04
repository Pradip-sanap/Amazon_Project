package com.user.Exception;

import com.user.Dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailExists(EmailAlreadyExistsException ex,HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse();
        response.setTimeStamp(LocalDateTime.now());
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setError("CONFLICT");
        response.setMessage(ex.getMessage());
        response.setPath(request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PhoneAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailExists(PhoneAlreadyExistsException ex,HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse();
        response.setTimeStamp(LocalDateTime.now());
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setError("CONFLICT");
        response.setMessage(ex.getMessage());
        response.setPath(request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}
