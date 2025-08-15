package rm.tech.ecommerce.module.ecommerce.domain.repositories.customizationfield;

import org.springframework.data.jpa.repository.JpaRepository;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.CustomizationField;

public interface CustomizationFieldRepository extends JpaRepository<CustomizationField, Long> {
}
