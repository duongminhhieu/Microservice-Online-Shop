package microservice.shop.orderservice.clients;

import microservice.shop.orderservice.dtos.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "inventory", url = "${inventory.url}")
public interface InventoryClient {

    @GetMapping(value = "/api/inventory")
    List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skuCodes);
}
