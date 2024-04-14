package rm.tech.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends Exception{
	
	public ValidationException(String message) {
		super(message);
	}

}
