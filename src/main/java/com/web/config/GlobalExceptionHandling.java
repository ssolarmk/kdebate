package com.web.config;

// ...existing code...

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandling {
	protected Logger logger;

	public GlobalExceptionHandling() {
		logger = LoggerFactory.getLogger(getClass());
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView common(Exception e) {

		e.printStackTrace();

		// ModelAndView mav = new ModelAndView();
		// mav.setViewName("/errors/404");
		// mav.addObject("exception", e);
		return null;
	}

}
