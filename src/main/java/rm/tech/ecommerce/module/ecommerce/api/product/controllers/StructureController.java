package rm.tech.ecommerce.module.ecommerce.api.product.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.SctructureRequest;
import rm.tech.ecommerce.module.ecommerce.services.structure.interfaces.IStructureService;

@RestController
@RequestMapping("/v1/structure")
@AllArgsConstructor
public class StructureController {

    private final IStructureService structureService;

    @PostMapping("/create")
    public void createStructure(@RequestBody SctructureRequest request) {
        structureService.create(request);
    }
}
