package rm.tech.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class ResourceAccessDeniedAPIException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceAccessDeniedAPIException(String message) {
		super(message);
	}

	
}
