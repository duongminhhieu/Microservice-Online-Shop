package microservice.shop.orderservice;

import io.restassured.RestAssured;
import microservice.shop.orderservice.stubs.InventoryClientStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {

    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mySQLContainer.start();
    }

    @Test
    void placeOrder_validRequest_success() {
        // given
        String submitOrderJson = """
                {
                    "orderLineItemsDtosList": [
                        {
                            "skuCode": "iphone_15",
                            "price": 1200,
                            "quantity": 12
                        }
                    ]
                }
                """;
        // when, then
        InventoryClientStub.stubInventoryCall(List.of("iphone_15"));

        var responseBodyString = RestAssured.given()
                .contentType("application/json")
                .body(submitOrderJson)
                .when()
                .post("/api/order")
                .then()
                .log().all()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .body()
                .asString();

        assertThat(responseBodyString).contains("Order Placed Successfully");
    }

}
