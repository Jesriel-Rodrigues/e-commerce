package rm.tech.ecommerce.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder 
@AllArgsConstructor
@NoArgsConstructor 
public class MessageCustom {

	private String timestamp;
	private Integer status;
	private Integer code;
	private String message;
	private Object content;
}
