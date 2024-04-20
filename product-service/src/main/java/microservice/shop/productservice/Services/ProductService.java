package microservice.shop.productservice.Services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.shop.productservice.DTOs.Requests.ProductRequest;
import microservice.shop.productservice.DTOs.Responses.ProductResponse;
import microservice.shop.productservice.Models.Product;
import microservice.shop.productservice.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product with ID {} created", product.getId());
    }


    public List<ProductResponse> getAllProducts() {

        List<Product> products =  productRepository.findAll();
        return products.stream()
                .map(this::mapProductToProductResponse)
                .toList();
    }

    private ProductResponse mapProductToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
