package rm.tech.ecommerce.module.ecommerce.services.product;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductRequest;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductPhoto;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.product.ProductRepository;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductService;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final ProductRepository productRepository;

    @Override
    public Product create(ProductRequest request){

        Product product = new Product(
                null,
                request.getName(),
                request.getDescription(),
                null,
                buildPhotos(request.getPhotos()));

        return productRepository.save(product);
    }


    private List<ProductPhoto> buildPhotos(List<String> photosRequest){
        AtomicLong priority = new AtomicLong(1);

        return photosRequest.stream()
                .map(photo -> new ProductPhoto(
                        null,
                        photo,
                        priority.getAndIncrement(),
                        null
                ))
                .toList();
    }



}
