package com.hcl.usf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * @author intakhabalam.s@hcl.com
 * @see ControllerAdvice as {@link ControllerAdvice}
 * @see ExceptionHandler as {@link ExceptionHandler}
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	/**
	 * 
	 * @param request
	 *            as {@link HttpServletRequest}
	 * @param ex
	 *            as {@link Exception}
	 * @return {@link String}
	 */
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Exception occured")
	@ExceptionHandler(Throwable.class)
	public String handleThException(HttpServletRequest request, Throwable ex) {
		request.setAttribute("param1", ex.getLocalizedMessage() + "{ }  " + " " + ex.getStackTrace()[0]);
		return "rediret:/errorpage";
	}

	/**
	 * 
	 * @param request
	 *            {@link HttpServletRequest}
	 * @param ex
	 *            {@link Exception}
	 * @return {@link String}
	 */
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Exception occured")
	@ExceptionHandler(Exception.class)
	public String handleException(HttpServletRequest request, Exception ex) {
		request.setAttribute("param1", ex.getLocalizedMessage() + "{ }  " + " " + ex.getStackTrace()[0]);
		return "rediret:/errorpage";
	}

	

	
	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

}
