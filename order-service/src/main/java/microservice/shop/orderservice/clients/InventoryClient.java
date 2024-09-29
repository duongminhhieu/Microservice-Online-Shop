package microservice.shop.orderservice.clients;

import microservice.shop.orderservice.dtos.InventoryResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface InventoryClient {

    @GetExchange(value = "/api/inventory")
    List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skuCodes);
}
