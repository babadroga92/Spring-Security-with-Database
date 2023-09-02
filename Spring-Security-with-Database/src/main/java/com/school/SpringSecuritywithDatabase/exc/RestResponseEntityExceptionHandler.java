package com.school.SpringSecuritywithDatabase.exc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WrongIdException.class)
    public ResponseEntity<ErrorMessage> wrongIdException(WrongIdException exception, WebRequest request){
        ErrorMessage message = new ErrorMessage( exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(ExceededNumberOfAdmins.class)
    public ResponseEntity<ErrorMessage> exceededNumberOfAdmins(ExceededNumberOfAdmins exception, WebRequest request){
        ErrorMessage message = new ErrorMessage( exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    @ExceptionHandler(DidntAddException.class)
    public ResponseEntity<ErrorMessage> didntAddException(DidntAddException exception, WebRequest request){
        ErrorMessage message = new ErrorMessage( exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(NotEnrolled.class)
    public ResponseEntity<ErrorMessage> notEnrolled(NotEnrolled exception, WebRequest request){
        ErrorMessage message = new ErrorMessage( exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(PasswordsDoNotMatch.class)
    public ResponseEntity<ErrorMessage> passwordsDoNotMatch(PasswordsDoNotMatch exception, WebRequest request){
        ErrorMessage message = new ErrorMessage( exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    @ExceptionHandler(UserWithUsernameAlreadyExists.class)
    public ResponseEntity<ErrorMessage> userWithUsernameAlreadyExists(UserWithUsernameAlreadyExists exception, WebRequest request){
        ErrorMessage message = new ErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    @ExceptionHandler(ImageDoesntExist.class)
    public ResponseEntity<ErrorMessage> imageDoesntExist(ImageDoesntExist exception, WebRequest request){
        ErrorMessage message = new ErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        ErrorMessage message = new ErrorMessage( errors.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
