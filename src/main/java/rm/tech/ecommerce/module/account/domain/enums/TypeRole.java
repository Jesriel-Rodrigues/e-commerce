package rm.tech.ecommerce.module.account.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TypeRole {
    
    DEFAULT(1L),
    ADMIN_STORE(2L),
    ADMIN_SYSTEM(3L);

    private Long size;
}
