package rm.tech.ecommerce.module.account.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    
    @Column(name = "zip_code")
    private String zipCode;

    private String street;

    private String city;

    private String state;

    @Column(name = "house_number")
    private String houseNumber;
}
