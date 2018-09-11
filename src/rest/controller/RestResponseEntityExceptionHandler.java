package rest.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import controller.BookController;

@ControllerAdvice
public class RestResponseEntityExceptionHandler 
  extends ResponseEntityExceptionHandler {
	final static Logger logger = Logger.getLogger(RestResponseEntityExceptionHandler.class);
 
    @ExceptionHandler(value = { RuntimeException.class })
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected Hashtable<String,String> handleConflict(
      RuntimeException ex, WebRequest request) {
    	StringWriter sw = new StringWriter();
    	ex.printStackTrace(new PrintWriter(sw));    	
    	logger.error(sw);
    	Hashtable<String,String> oTable = new Hashtable<String,String>();
        String bodyOfResponse = "This should be application specific";
        oTable.put("message",bodyOfResponse);
        oTable.put("error","1");
        oTable.put("trace",ex.getMessage());
        oTable.put("name",ex.getClass().getName());
        return oTable;
    }
    
 
}	