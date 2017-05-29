package it.ninebee.lasa.laisfala.web.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import it.ninebee.lasa.laisfala.web.exception.LaisException;
import it.ninebee.web.util.RestEnvelopeControllerAdvise;

@ControllerAdvice(annotations=RestController.class)
@Component
public class RestControllerAdvice extends RestEnvelopeControllerAdvise{

	 @ExceptionHandler(LaisException.class)
	  public static ResponseEntity<Object> handleBusinessExceptions(Exception ex,  HttpServletRequest request) {
	    return handleExceptionInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);    
	  }
	
}
