package microservice.shop.productservice.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.shop.productservice.dtos.requests.ProductRequest;
import microservice.shop.productservice.dtos.responses.ProductResponse;
import microservice.shop.productservice.mappers.ProductMapper;
import microservice.shop.productservice.models.Product;
import microservice.shop.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        product = productRepository.save(product);

        log.info("Product with ID {} created", product.getId());
        return productMapper.toProductResponse(product);
    }


    public List<ProductResponse> getAllProducts() {
        List<Product> products =  productRepository.findAll();
        return products.stream()
                .map(productMapper::toProductResponse)
                .toList();
    }
}
