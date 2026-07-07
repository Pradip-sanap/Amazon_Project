package com.user.Exception;

import com.user.Dto.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.UnexpectedTypeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import tools.jackson.databind.exc.UnrecognizedPropertyException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorResponse> runtimeErrorHandler(RuntimeException ex, HttpServletRequest request){
//        ErrorResponse response = ErrorResponse.builder()
//                .timeStamp(LocalDateTime.now())
//                .error()
//
//    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailExists(EmailAlreadyExistsException ex,HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setTimeStamp(LocalDateTime.now());
        response.setError("CONFLICT");
        response.setMessage(ex.getMessage());
        response.setPath(request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PhoneAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailExists(PhoneAlreadyExistsException ex,HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setTimeStamp(LocalDateTime.now());
        response.setError("CONFLICT");
        response.setMessage(ex.getMessage());
        response.setPath(request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setTimeStamp(LocalDateTime.now());
        response.setError("VALIDATION_FAILED");

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        response.setMessage(errors.toString());
        response.setPath(request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEnum(HttpMessageNotReadableException ex, HttpServletRequest request) {

        Throwable cause = ex.getMostSpecificCause();

        String message = "Invalid request format";

        if (cause.getMessage().contains("Role")) {
            message = "Invalid Role.";
        }
        if (ex.getMessage().contains("LocalDate")) {
            message = "Invalid Date.";
        }
        if (cause instanceof UnrecognizedPropertyException propertyException) {
             message = "Invalid field: " + propertyException.getPropertyName();
        }
        if(ex.getMessage().contains("UUID")){
            message = "Invalid UserId provided";
        }

        ErrorResponse response = new ErrorResponse();
        response.setTimeStamp(LocalDateTime.now());
        response.setError("VALIDATION_FAILED");
        response.setMessage(message);
        response.setPath(request.getRequestURI());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request){
        ErrorResponse response = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .error("INVALID_INPUT")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(UserNotFoundException ex, HttpServletRequest request){
        ErrorResponse response = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .error("INVALID_USERID")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                "METHOD_NOT_ALLOWED",
                "HTTP method not supported",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFound(NoResourceFoundException ex, HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                "NOT_FOUND",
                "Resource not found",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleConstraint(DataIntegrityViolationException ex) {

        return ResponseEntity.badRequest()
                .body("Database constraint violated.");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
