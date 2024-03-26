package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.ErrorAskNoSave;
import com.example.demo.exception.ErrorAskNoUpdate;
import com.example.demo.exception.ErrorOptionTestNull;

@ControllerAdvice
public class ErrorHandlerController {
	
	@ExceptionHandler(ErrorOptionTestNull.class) 
	public String opcionNoSeleccionada (ErrorOptionTestNull err, Model model) {
		model.addAttribute("message", err.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", LocalDate.now());
		
		return "error/ErrorOptionTestNull"; 
	}
	
	@ExceptionHandler(ErrorAskNoSave.class) 
	public String formularioNoRellenado (ErrorAskNoSave err, Model model) {
		model.addAttribute("message", err.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", LocalDate.now());
		
		return "error/ErrorAskNoSave"; 
	}
	
	@ExceptionHandler(ErrorAskNoUpdate.class) 
	public String preguntaNoGuardada (ErrorAskNoUpdate err, Model model) {
		model.addAttribute("message", err.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", LocalDate.now());
		
		return "error/ErrorNoUpdate"; 
	}

}
