package br.com.magna.musicaapi.infra;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratarErros {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> erro404() {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrosDTO>> erro400(MethodArgumentNotValidException exception) {
		var erros = exception.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(ErrosDTO::new).toList());
	}

	private record ErrosDTO(String campo, String mensangem) {
		public ErrosDTO(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
}
