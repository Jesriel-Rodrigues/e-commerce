package rm.tech.ecommerce.module.account.domain.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Account {
    
    private Long id;

    private String email;
}
