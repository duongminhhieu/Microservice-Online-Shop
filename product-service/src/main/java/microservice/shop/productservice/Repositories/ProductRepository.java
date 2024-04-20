package microservice.shop.productservice.Repositories;

import microservice.shop.productservice.Models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>{
}
