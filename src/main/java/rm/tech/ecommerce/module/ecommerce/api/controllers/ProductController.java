package rm.tech.ecommerce.module.ecommerce.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rm.tech.ecommerce.module.ecommerce.domain.entities.Product;


@RestController
@RequestMapping("/v1/product")
public class ProductController {
    

    @PostMapping("/create")
    public Product createProduct() {
        
        return new Product();    
    }

}
