package rm.tech.ecommerce.module.ecommerce.api.product.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductRequest;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductService;


@RestController
@RequestMapping("/v1/product")
@AllArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping("/create")
    public void createProduct(@RequestBody ProductRequest request) {
        productService.create(request);
    }

}
