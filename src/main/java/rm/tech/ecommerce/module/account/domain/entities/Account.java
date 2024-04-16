package rm.tech.ecommerce.module.account.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Account {
    
    @Id
    private Long id;

    private String email;

    private String password;
}
