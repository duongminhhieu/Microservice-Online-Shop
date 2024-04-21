package microservice.shop.inventoryservice.Controllers;

import lombok.RequiredArgsConstructor;
import microservice.shop.inventoryservice.DTOs.Responses.InventoryResponse;
import microservice.shop.inventoryservice.Services.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;


    // http://localhost:8082/api/inventory?skuCode=sku1&skuCode=sku2
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skuCodes) {
        return inventoryService.isInStock(skuCodes);
    }
}
