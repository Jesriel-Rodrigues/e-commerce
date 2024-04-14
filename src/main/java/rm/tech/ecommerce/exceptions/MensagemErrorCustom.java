package rm.tech.ecommerce.exceptions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MensagemErrorCustom {

	private String timestamp;
	private Integer status;
	private Integer codigo;
	private String error;
	private String message;
	private String path;
	private List<ErrorObject> errors;

}
