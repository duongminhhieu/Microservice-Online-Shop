package microservice.shop.productservice.Controllers;


import lombok.RequiredArgsConstructor;
import microservice.shop.productservice.DTOs.Requests.ProductRequest;
import microservice.shop.productservice.DTOs.Responses.ProductResponse;
import microservice.shop.productservice.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }


}
