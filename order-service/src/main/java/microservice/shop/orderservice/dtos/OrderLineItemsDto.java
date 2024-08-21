package microservice.shop.orderservice.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLineItemsDto {
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
