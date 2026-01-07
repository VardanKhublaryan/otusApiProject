import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;

import dto.OrderRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.OrderService;

public class OrderTest {

   @Test
   @DisplayName("Validate order request schema")
   void validateOrderRequestSchema() {
      OrderRequest request =
          OrderRequest.buildOrderRequest(
              1,
              0,
              0,
              "2025-12-29T07:43:25.186Z",
              "placed",
              true
          );

      new OrderService().createOrder(request)
          .then()
          .statusCode(200)
          .body(
              matchesJsonSchemaInClasspath(
                  "schemas/order-schema.json"
              )
          );
   }

   @Test
   @DisplayName("Validate order request schema")
   void validateOrderRequestWithWrongDate() {
      OrderRequest request =
          OrderRequest.buildOrderRequest(
              1,
              0,
              0,
              "2025-29T07:43:25.186Z",
              "placed",
              true
          );

      new OrderService().createOrder(request)
          .then()
          .statusCode(500).body("message",
          equalTo("something bad happened"));
   }

   @Test
   @DisplayName("Delete order")
   public void deleteOrder() {
      int orderId = 1;

      OrderRequest request =
          OrderRequest.buildOrderRequest(
              orderId,
              0,
              0,
              "2025-12-29T07:43:25.186Z",
              "placed",
              true
          );

      new OrderService()
          .createOrder(request)
          .then()
          .statusCode(200);

      await()
          .atMost(10, SECONDS)
          .pollInterval(1, SECONDS)
          .until(() ->
              new OrderService()
                  .getOrder(orderId)
                  .getStatusCode() == 200
          );

      new OrderService()
          .deleteOrder(orderId)
          .then()
          .statusCode(200)
          .body("message", equalTo(String.valueOf(orderId)));
   }

   @Test
   @DisplayName("Delete wrong order")
   public void deleteWrongOrder()  {
      new OrderService().deleteOrder(555).then()
          .statusCode(404)
          .body("message",equalTo("Order Not Found"));
   }

}
