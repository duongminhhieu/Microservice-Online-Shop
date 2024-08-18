package microservice.shop.productservice.mappers;

import microservice.shop.productservice.dtos.requests.ProductRequest;
import microservice.shop.productservice.dtos.responses.ProductResponse;
import microservice.shop.productservice.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductRequest productCreation);
    ProductResponse toProductResponse(Product product);
}
