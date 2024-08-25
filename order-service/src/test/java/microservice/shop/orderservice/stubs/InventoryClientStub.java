package microservice.shop.orderservice.stubs;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub {

    public static void stubInventoryCall(List<String> skuCodes) {

        stubFor(get(urlEqualTo("/api/inventory?skuCode=" + String.join("&skuCode=", skuCodes)))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("""
                                [
                                    {
                                        "skuCode": "iphone_15",
                                        "isInStock": true,
                                        "quantity": 100
                                    }
                                ]
                                """)));
    }
}
