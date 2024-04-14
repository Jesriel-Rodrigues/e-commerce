package rm.tech.ecommerce.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MensagemErrorFeignCustom {

	private String timestamp;
	private String datahora;
	private Integer status;
	private Integer codigo;
	private Integer code;
	private String error;
	private String erro;
	private String message;
	private String mensagem;
	private String mensagemDev;
	private String path;
	private List<ErrorObjectFeign> campos;
	private List<ErrorObject> errors;

}
