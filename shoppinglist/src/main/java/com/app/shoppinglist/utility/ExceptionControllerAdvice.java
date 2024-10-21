package com.app.shoppinglist.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.shoppinglist.exception.ShoppingListException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import org.apache.commons.logging.LogFactory;

import java.util.stream.Collectors;

import org.apache.commons.logging.Log;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final Log Logger = LogFactory.getLog(ExceptionControllerAdvice.class); 
	@Autowired
	private Environment environment; 
	
	
@ExceptionHandler(ShoppingListException.class)
public ResponseEntity <ErrorInfo> meetingSchedulerExceptionHandler(ShoppingListException exception)
{
	System.out.println("in shoppinglistexception.class");
	Logger.error(exception.getMessage(), exception); 
	ErrorInfo errorInfo = new ErrorInfo(); 
	errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
	errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
	return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST); 
	}

@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception)
{
	System.out.println("in generel exception");
	System.out.println(exception.getMessage());
	System.out.println(ShoppingListException.class);
	System.out.println(exception.getLocalizedMessage());
Logger.error(exception.getMessage(), exception); 
ErrorInfo errorInfo = new ErrorInfo(); 
errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
errorInfo.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
return new ResponseEntity<> (errorInfo, HttpStatus.INTERNAL_SERVER_ERROR ); 
}

@ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception exception)
{
	System.out.println("in the other");
Logger.error(exception.getMessage(), exception);
String errorMsg; 
if(exception instanceof MethodArgumentNotValidException)
{
MethodArgumentNotValidException manvException = (MethodArgumentNotValidException) exception;
errorMsg = manvException.getBindingResult()
.getAllErrors()
.stream()
.map(ObjectError::getDefaultMessage)
.collect(Collectors.joining(", ")); 
}
else
{
ConstraintViolationException cvException = (ConstraintViolationException) exception;
errorMsg = cvException.getConstraintViolations()
.stream()
.map(ConstraintViolation::getMessage)
.collect(Collectors.joining(", ")); 
}

ErrorInfo errorInfo = new ErrorInfo(); 
errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
errorInfo.setErrorMessage(errorMsg);
return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST); 
}


}
