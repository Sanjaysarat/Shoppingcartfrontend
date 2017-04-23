package com.niit.sanshoppingcartfrontend.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.hql.internal.ast.QuerySyntaxException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.sun.istack.internal.logging.Logger;

@ControllerAdvice
public class ShoppingCartExceptionalHandler {

	@ExceptionHandler(SQLException.class)
	public ModelAndView handleSQLException(Exception e) {

		ModelAndView mv = new ModelAndView("error");
		// ModelAndView mv = new ModelAndView("error");
		mv.addObject("message", "It seems one of the table OR few fields does not exist in DB. "
				+ "  See the logger for more information");
		mv.addObject("errorMessage", e.getMessage());

		return mv;
	}

	@ExceptionHandler(CannotCreateTransactionException.class)
	public ModelAndView dbServerNotStarted(Exception e) {

		ModelAndView mv = new ModelAndView("error");
		// ModelAndView mv = new ModelAndView("error");
		mv.addObject("message", "It seems Database server not started");
		mv.addObject("errorMessage", e.getMessage());

		return mv;
	}

	@ExceptionHandler(QuerySyntaxException.class)
	public ModelAndView handleQuerySyntaxException(Exception e) {

		ModelAndView mv = new ModelAndView("error");
		// ModelAndView mv = new ModelAndView("error");
		mv.addObject("message", "It seems one of the query is not proper See the logger for more information");
		mv.addObject("errorMessage", e.getMessage());

		return mv;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView noHandlerException(HttpServletRequest request, Exception e) {

		ModelAndView mv = new ModelAndView("error");
		// ModelAndView mv = new ModelAndView("error");
		mv.addObject("message", "No handler found.  Invalid URL See the logger for more information");

		mv.addObject("errorMessage", e.getMessage());

		return mv;
	}

	// @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
	@ExceptionHandler(IOException.class)
	public ModelAndView handleIOException(Exception e) {

		ModelAndView mv = new ModelAndView("error");
		// ModelAndView mv = new ModelAndView("error");
		mv.addObject("message", "Not able to connect to server.  please contact administration");

		mv.addObject("errorMessage", e.getMessage());

		return mv;

	}
}
