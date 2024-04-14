package rm.tech.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.GONE)
public class ResourceExpiredJwtException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceExpiredJwtException(String message) {
		super(message);
	}

}
