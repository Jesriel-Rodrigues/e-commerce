package rm.tech.ecommerce.module.ecommerce.services;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.api.dto.request.ProductRequest;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product;
import rm.tech.ecommerce.module.ecommerce.services.interfaces.IProductService;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService{
    

    public Product create(ProductRequest request){
        
    }
}
