package com.app.shoppinglist.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.app.shoppinglist.exception.ShoppingListException;
@Component
@Aspect
public class LoggingAspect {

	   private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

	    @AfterThrowing(pointcut = "execution(* com.infy.service.*Impl.*(..))", throwing = "exception")
	    public void logServiceException(ShoppingListException exception)
	    {
	    	LOGGER.error(exception.getMessage(), exception);
		// code
	    }
}
