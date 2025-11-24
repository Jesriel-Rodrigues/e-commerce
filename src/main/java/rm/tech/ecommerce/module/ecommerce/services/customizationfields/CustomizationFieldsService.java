package rm.tech.ecommerce.module.ecommerce.services.customizationfields;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rm.tech.ecommerce.exceptions.ResourceNotFoundException;
import rm.tech.ecommerce.module.ecommerce.api.customization.dto.request.CustomizationFieldRequest;
import rm.tech.ecommerce.module.ecommerce.api.customization.dto.request.SelectItemRequest;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.CustomizationField;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.SelectItem;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.enums.CustomizationFieldsStatus;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.enums.SelectItemStatus;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.customizationfield.CustomizationFieldRepository;
import rm.tech.ecommerce.module.ecommerce.services.customizationfields.interfaces.ICustomizationFieldsService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomizationFieldsService implements ICustomizationFieldsService {

    private final CustomizationFieldRepository customizationFieldRepository;


    @Override
    public Optional<CustomizationField> findOptionalById(Long id) {
        return customizationFieldRepository.findById(id);
    }

    @Override
    public CustomizationField findById(Long id) {
        return customizationFieldRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "CustomizationField not found with id: " + id
                ));
    }

    @Override
    public CustomizationField create(CustomizationFieldRequest request){

        CustomizationField customizationField = new CustomizationField(
                request.getName(),
                request.getCustomType(),
                request.isNotNull(),
                request.getSequence(),
                CustomizationFieldsStatus.ENABLE,
                buildItems(request.getItems()),
                request.getValue()
        );
        return customizationFieldRepository.save(customizationField);
    }

    public List<SelectItem> buildItems(List<SelectItemRequest> items){

        return items.stream()
                .map(item -> new SelectItem(
                        item.getName(),
                        item.getDescription(),
                        SelectItemStatus.ENABLE,
                        item.getValue(),
                        null
                ))
                .toList();
    }

}
