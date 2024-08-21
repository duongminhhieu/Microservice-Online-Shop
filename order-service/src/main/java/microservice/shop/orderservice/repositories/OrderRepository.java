package microservice.shop.orderservice.repositories;

import microservice.shop.orderservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends  JpaRepository<Order, Long> {
}
