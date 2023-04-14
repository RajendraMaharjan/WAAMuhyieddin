package edu.miu.waalab.errors.exceptionhandler;

import edu.miu.waalab.errors.CustomMessage;
import edu.miu.waalab.errors.customexception.ItemNotFoundException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseStatus
public class ResponseEntityExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<?> itemNotFoundException(ItemNotFoundException ex, WebRequest wq) {
        CustomMessage customMessage = new CustomMessage(ex.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(customMessage);
    }

    @ExceptionHandler(value = BadCredentialsException.class) // when Invalid Credentials
    public ResponseEntity<ErrorMessage> handleInvalidCredentialsException(
            BadCredentialsException e) {
        return new ResponseEntity<>(
                new ErrorMessage(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
