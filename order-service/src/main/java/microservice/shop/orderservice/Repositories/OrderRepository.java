package microservice.shop.orderservice.Repositories;

import microservice.shop.orderservice.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends  JpaRepository<Order, Long> {
}
