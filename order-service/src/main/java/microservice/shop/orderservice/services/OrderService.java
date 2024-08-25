package microservice.shop.orderservice.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.shop.orderservice.clients.InventoryClient;
import microservice.shop.orderservice.dtos.InventoryResponse;
import microservice.shop.orderservice.dtos.OrderLineItemsDto;
import microservice.shop.orderservice.dtos.requests.OrderRequest;
import microservice.shop.orderservice.models.Order;
import microservice.shop.orderservice.models.OrderLineItems;
import microservice.shop.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @Transactional
    public void placeOrder(OrderRequest orderRequest) {

        List<InventoryResponse> inventoryResponses = inventoryClient.isInStock(orderRequest.getOrderLineItemsDtosList()
                .stream()
                .map(OrderLineItemsDto::getSkuCode)
                .toList());

        if (inventoryResponses.isEmpty() || inventoryResponses.size() != orderRequest.getOrderLineItemsDtosList().size()) {
            throw new RuntimeException("Product is not in stock, please try again later.");
        }

        boolean clientInStock = inventoryResponses.stream().allMatch(InventoryResponse::getIsInStock);

        if (clientInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());

            List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtosList()
                    .stream()
                    .map(this::mapToOrderLineItems)
                    .toList();
            order.setOrderLineItems(orderLineItems);

            orderRepository.save(order);
        } else {
            throw new RuntimeException(("Product is not in stock, please try again later."));
        }
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }

}
