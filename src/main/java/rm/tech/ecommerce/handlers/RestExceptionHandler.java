package rm.tech.ecommerce.handlers;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import rm.tech.ecommerce.exceptions.ErrorObject;
import rm.tech.ecommerce.exceptions.MensagemErrorCustom;
import rm.tech.ecommerce.exceptions.MessageCustom;
import rm.tech.ecommerce.exceptions.ResourceAccessDeniedAPIException;
import rm.tech.ecommerce.exceptions.ResourceBadRequestException;
import rm.tech.ecommerce.exceptions.ResourceForbiddenException;
import rm.tech.ecommerce.exceptions.ResourceNotFoundException;
import rm.tech.ecommerce.exceptions.ResourceUnauthorizedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

	private List<ErrorObject> getErrors(final MethodArgumentNotValidException ex) {

		final List<ErrorObject> erros = new ArrayList<ErrorObject>();

		ex.getBindingResult().getFieldErrors().stream().forEach(error -> {
			final ErrorObject er = new ErrorObject();
			er.setMessage(error.getDefaultMessage());
			er.setField(error.getField());
			er.setParameter(error.getRejectedValue());
			erros.add(er);
		});

		return erros;
	}


	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(final ResourceNotFoundException exception,
			final ServletRequest request) {
		final String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
		
		final MensagemErrorCustom custom = MensagemErrorCustom.builder()
				.timestamp(data)
				.status(HttpStatus.NOT_FOUND.value())
				.error(HttpStatus.NOT_FOUND.name())
				.message(exception.getMessage())
				.path(((HttpServletRequest) request).getRequestURI().toString())
				.build();

		return new ResponseEntity<>(custom, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceUnauthorizedException.class)
	public ResponseEntity<?> handlerResourceUnauthorizedException(final ResourceUnauthorizedException exception,
			final ServletRequest request) {
		final String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
		
		final MensagemErrorCustom custom = MensagemErrorCustom.builder()
				.timestamp(data)
				.status(HttpStatus.UNAUTHORIZED.value())
				.error(HttpStatus.UNAUTHORIZED.name())
				.message(exception.getMessage())
				.path(((HttpServletRequest) request).getRequestURI().toString())
				.build();

		return new ResponseEntity<>(custom, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ResourceForbiddenException.class)
	public ResponseEntity<?> handlerResourceForbiddenException(final ResourceForbiddenException exception,
			final ServletRequest request) {
		final String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));

		final MensagemErrorCustom custom = MensagemErrorCustom.builder()
				.timestamp(data)
				.status(HttpStatus.FORBIDDEN.value())
				.error(HttpStatus.FORBIDDEN.name())
				.message(exception.getMessage())
				.path(((HttpServletRequest) request).getRequestURI().toString())
				.build();

		return new ResponseEntity<>(custom, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(ResourceBadRequestException.class)
	public ResponseEntity<?> handlerResourceBadRequestException(final ResourceBadRequestException exception,
			final ServletRequest request) {
		final String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));

		final MensagemErrorCustom custom = MensagemErrorCustom.builder()
				.timestamp(data)
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.name())
				.message(exception.getMessage())
				.path(((HttpServletRequest) request).getRequestURI().toString())
				.build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            .contentType(MediaType.APPLICATION_JSON)
	            .body(custom);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerMethodArgumentNotValidException(final MethodArgumentNotValidException exception,
			final ServletRequest request) {

		final List<ErrorObject> errors = getErrors(exception);

		final String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));

		final MensagemErrorCustom custom = MensagemErrorCustom.builder()
				.timestamp(data)
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.name())
				.message("Requisição possui campos inválidos")
				.path(((HttpServletRequest) request).getRequestURI().toString())
				.errors(errors)
				.build();

		return new ResponseEntity<>(custom, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceAccessDeniedAPIException.class)
	public ResponseEntity<?> handlerMethodResourceAccessDeniedAPIException(ResourceAccessDeniedAPIException exception, ServletRequest request) {
		final String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));

		final MessageCustom custom = MessageCustom.builder()
				.timestamp(data)
				.code(4003)
				.status(HttpStatus.FORBIDDEN.value())
				.message(exception.getMessage())
				.build();

		return new ResponseEntity<>(custom, HttpStatus.OK);
	}
}
