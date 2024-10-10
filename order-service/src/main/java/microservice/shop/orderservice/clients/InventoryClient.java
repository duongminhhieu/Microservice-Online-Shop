package microservice.shop.orderservice.clients;

import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import microservice.shop.orderservice.dtos.InventoryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@Slf4j
public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange(value = "/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skuCodes);

    default List<InventoryResponse> fallbackMethod(List<String> skuCodes, Throwable throwable) {
        log.error("Fallback method for inventory client for  skuCodes: {}, cause - {}",
                skuCodes, throwable.getMessage());
        return List.of();
    }
}
