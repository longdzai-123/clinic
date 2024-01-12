package vn.oceantech.l3pre.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.oceantech.l3pre.common.Response;

@ControllerAdvice
public class ProExceptionHandler {
    @ExceptionHandler(ProException.class)
    protected ResponseEntity<Object> handleDuplicateException(ProException proException) {
        return new ResponseEntity<>(Response.buildResponse(proException.getErrorMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(DuplicateException.class)
    protected ResponseEntity<Object> handleDuplicateException(DuplicateException duplicateException) {
        return new ResponseEntity<>(Response.buildResponse(duplicateException.getErrorMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleDuplicateException(NotFoundException notFoundException) {
        return new ResponseEntity<>(Response.buildResponse(notFoundException.getErrorMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(ConfirmBookingException.class)
    protected ResponseEntity<Object> handleConfirmBookingException(ConfirmBookingException confirmBookingException) {
        return new ResponseEntity<>(Response.buildResponse(confirmBookingException.getErrorMessage()), HttpStatus.OK);
    }
}
