package br.dev.oliveiratec.client.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.dev.oliveiratec.client.services.exceptions.DadosNotFoundException;

@ControllerAdvice
public class ResourceExcecoesManipuladas {
	
	@ExceptionHandler(DadosNotFoundException.class)
	public ResponseEntity<StandardError> DadosNaoEncontrado(DadosNotFoundException e, HttpServletRequest request){
		StandardError statusError = new StandardError();
		statusError.setTimestamp(Instant.now());
		statusError.setStatus(HttpStatus.NOT_FOUND.value());
		statusError.setError("Resource not found");
		statusError.setMessage(e.getMessage());
		statusError.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(statusError);		
	}	
}
