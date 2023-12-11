package com.seisbot.prova.excecao;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> tratamentoExcecaoValidacao(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String atributo = ((FieldError) error).getField();
			String mensagem = error.getDefaultMessage();
			errors.put(atributo, mensagem);
		});
		return errors;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(RepetilNotFoundException.class)
	public Map<String, String> repetilNotFoundException(RepetilNotFoundException ex){
		Map<String, String> errosMap = new HashMap<String, String>();
		errosMap.put("mensagem", ex.getMessage());
		return errosMap;
	}
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(StringIndexOutOfBoundsException.class)
	public Map<String, String> indexNotFound(StringIndexOutOfBoundsException ex){
		System.out.println("Na global");
		Map<String, String> errosMap = new HashMap<String, String>();
		errosMap.put("mensagem", ex.getMessage());
		return errosMap;
	}
}
