package rm.tech.ecommerce.module.ecommerce.api.customization.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rm.tech.ecommerce.module.ecommerce.api.customization.dto.request.CustomizationFieldRequest;
import rm.tech.ecommerce.module.ecommerce.services.customizationfields.interfaces.ICustomizationFieldsService;

@RestController
@RequestMapping("/v1/customizationField")
@AllArgsConstructor
public class CustomizationFieldController {

    private final ICustomizationFieldsService customizationFieldsService;

    @PostMapping("/create")
    public void createProduct(@RequestBody CustomizationFieldRequest request) {
        customizationFieldsService.create(request);
    }
}
