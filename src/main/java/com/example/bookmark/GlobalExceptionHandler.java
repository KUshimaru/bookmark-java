package com.example.bookmark;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView handleIllegalArgumentException(IllegalArgumentException ex) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("error", ex.getMessage());
		modelAndView.setViewName("errorPage"); // エラーメッセージを表示するビュー名
		return modelAndView;
	}
}
