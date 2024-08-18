package microservice.shop.productservice;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {


    @ServiceConnection // auto-injects the service connection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mongoDBContainer.start();
    }


    @Nested
    class HappyCase{
        @Test
        void testCreateProduct_validRequest_returnCreated() {
            String requestBody = """
                    {
                        "name": "Product 1",
                        "description": "Description 1",
                        "price": 100
                    }
                    """; // JSON request body

            RestAssured.given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(requestBody)
                    .when()
                    .post("/api/product")
                    .then()
                    .statusCode(201)
                    .body("id", Matchers.notNullValue())
                    .body("name", Matchers.equalTo("Product 1"))
                    .body("description", Matchers.equalTo("Description 1"))
                    .body("price", Matchers.equalTo(100));

        }
    }
}
