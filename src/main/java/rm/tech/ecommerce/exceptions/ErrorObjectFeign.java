package rm.tech.ecommerce.exceptions;

import lombok.Data;

@Data
public class ErrorObjectFeign {

	private String mensagem;
	private String campo;
	private Object parametro;
}
