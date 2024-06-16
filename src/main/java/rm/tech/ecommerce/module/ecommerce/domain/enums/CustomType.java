package rm.tech.ecommerce.module.ecommerce.domain.enums;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum CustomType {


    LONG("Numero inteiro", Long.class),
	BIG_DECIMAL("Numero decimal", BigDecimal.class),
	BOOLEAN("Boleano", Boolean.class),
	LOCAL_DATE("Data", LocalDateTime.class),
	STRING("Caractere", String.class),
    TEXT_AREA("Texto", String.class),
    SELECT_ITEM("Selecione o item", List.class);
	
	private final String description;
	private final Object typeClass;

}
