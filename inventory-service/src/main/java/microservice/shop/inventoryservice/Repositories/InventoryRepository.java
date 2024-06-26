package microservice.shop.inventoryservice.Repositories;


import microservice.shop.inventoryservice.DTOs.Responses.InventoryResponse;
import microservice.shop.inventoryservice.Models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findBySkuCode(String skuCode);

    List<Inventory> findBySkuCodeIn(List<String> skuCodes);

}
