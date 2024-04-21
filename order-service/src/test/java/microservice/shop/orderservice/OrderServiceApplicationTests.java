package microservice.shop.orderservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservice.shop.orderservice.DTOs.OrderLineItemsDto;
import microservice.shop.orderservice.DTOs.Requests.OrderRequest;
import microservice.shop.orderservice.Repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class OrderServiceApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private OrderRepository orderRepository;

    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:5.7");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }


    @Test
    void shouldPlaceOrder() throws Exception {
        OrderRequest orderRequest = getOrderRequest();
        String orderRequestString = objectMapper.writeValueAsString(orderRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderRequestString))
                .andExpect(status().isCreated());

        Assertions.assertEquals(1, orderRepository.count());

    }


    private OrderRequest getOrderRequest() {
        OrderRequest orderRequest = new OrderRequest();

        List<OrderLineItemsDto> orderLineItemsDtoList = List.of(
                OrderLineItemsDto.builder()
                        .skuCode("iphone_10")
                        .quantity(2)
                        .price(BigDecimal.valueOf(1000))
                        .build(),
                OrderLineItemsDto.builder()
                        .skuCode("iphone_11")
                        .quantity(1)
                        .price(BigDecimal.valueOf(1200))
                        .build()

        );

        orderRequest.setOrderLineItemsDtosList(orderLineItemsDtoList);
        return orderRequest;
    }


}
