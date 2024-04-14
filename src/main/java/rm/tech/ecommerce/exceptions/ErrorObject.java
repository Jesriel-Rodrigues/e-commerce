package rm.tech.ecommerce.exceptions;

import lombok.Data;

@Data
public class ErrorObject {

	private String message;
	private String field;
	private Object parameter;
}
